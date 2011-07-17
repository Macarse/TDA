package com.tda.presentation.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.Authority;
import com.tda.model.applicationuser.OnlineUser;
import com.tda.model.item.Item;
import com.tda.model.item.ItemBuilder;
import com.tda.model.itinerary.Itinerary;
import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.model.patient.Sex;
import com.tda.model.pediatrician.PediatricianDiagnosis;
import com.tda.persistence.dao.ItineraryDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.presentation.params.ParamContainer;
import com.tda.service.api.ApplicationUserService;
import com.tda.service.api.ItemService;
import com.tda.service.api.OnlineUserService;
import com.tda.service.api.PatientInTrainService;
import com.tda.service.api.PatientService;
import com.tda.service.api.PediatricianDiagnosisService;

@Controller
@RequestMapping(value = "/")
@SessionAttributes({ "patient", "user", "currentItinerary" })
public class WelcomeController {
	private static final String LIST = "welcome/list";

	private PatientInTrainService patientInTrainService;
	private PatientService patientService;
	private ItemService itemService;
	private PediatricianDiagnosisService pediatricianDiagnosisService;
	private ApplicationUserService applicationUserService;
	private Paginator paginator;
	private ParamContainer params;

	// TODO should be localized?
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	private OnlineUserService onlineUserService;
	private ItineraryDAO itineraryDAO;

	public WelcomeController() {
		params = new ParamContainer();
	}

	@Autowired
	public void setApplicationUserService(
			ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

	@ModelAttribute("sex")
	public Sex[] populateCategories() {
		return Sex.values();
	}

	@ModelAttribute("currentItinerary")
	public Itinerary getCurrentItinerary() {
		Itinerary itinerary = itineraryDAO.findNextItinerary();

		if (itinerary == null) {
			return new Itinerary();
		} else {
			return itinerary;
		}
	}

	@ModelAttribute("user")
	public UserDetails getUser() {
		Object aux = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return ((UserDetails) aux);
	}

	@ModelAttribute("userList")
	public List<ApplicationUser> getUserList() {
		List<ApplicationUser> users = applicationUserService.findAll();

		// set admin authority
		Authority adminAuth = new Authority();
		adminAuth.setAuthority("ROLE_ADMIN");

		// ROLE_USER
		Authority userAuth = new Authority();
		userAuth.setAuthority("ROLE_USER");

		Iterator<ApplicationUser> iter = users.iterator();
		ApplicationUser user;

		while (iter.hasNext()) {
			user = iter.next();
			Collection<Authority> auths = user.getMyAuthorities();

			if (auths.size() == 2 && auths.contains(adminAuth)
					&& auths.contains(userAuth)) {
				iter.remove();
			}
		}

		return users;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getList(
			@RequestParam(value = "page", required = false) Integer pageNumber,
			@RequestParam(value = "orderField", required = false) String orderField,
			@RequestParam(value = "orderAscending", required = false) Boolean orderAscending) {
		ModelAndView modelAndView = new ModelAndView(LIST);

		modelAndView = processRequest(modelAndView, new PatientInTrain(),
				pageNumber, orderField, orderAscending);

		return modelAndView;
	}

	@RequestMapping(value = "/getOnlineUsers", method = RequestMethod.GET)
	public @ResponseBody
	String getOnlineUsers() {
		// clear offline users
		onlineUserService.clearOffline();

		// update timeout
		onlineUserService.setOnline(getUser().getUsername());

		// return online users
		Gson gson = new Gson();
		List<OnlineUser> users = onlineUserService.getOnlineUsers();
		// remove me from the list
		OnlineUser me = new OnlineUser();
		me.setUsername(getUser().getUsername());

		users.remove(me);

		return gson.toJson(users);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		onlineUserService.setOffline(getUser().getUsername());
		return "redirect:/j_spring_security_logout";
	}

	@RequestMapping(value = "/switchInTrain", method = RequestMethod.POST)
	public @ResponseBody
	String switchInTrain(@RequestParam Long patientId) {

		Patient aPatient = patientService.findById(patientId);
		PatientInTrain aPatientInTrain = patientInTrainService
				.findByPatient(aPatient);

		/* The patient is already in the train. */
		if (aPatientInTrain != null) {
			patientInTrainService.delete(aPatientInTrain);
			return "Subir";
		} else {
			aPatientInTrain = new PatientInTrain();
			aPatientInTrain.setPatient(aPatient);
			patientInTrainService.save(aPatientInTrain);
			return "Bajar";
		}
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView getList(
			@ModelAttribute PatientInTrain aPatient,
			BindingResult result,
			@RequestParam(value = "page", required = false) Integer pageNumber,
			@RequestParam(value = "orderField", required = false) String orderField,
			@RequestParam(value = "orderAscending", required = false) Boolean orderAscending) {

		ModelAndView modelAndView = new ModelAndView(LIST);

		// set first page paginator
		paginator.setPageIndex(1);

		// if (aPatient.getFirstName() != null)
		// params.setParam("firstName", aPatient.getFirstName());
		// if (aPatient.getLastName() != null)
		// params.setParam("lastName", aPatient.getLastName());
		// if (aPatient.getDni() != null)
		// params.setParam("dni", aPatient.getDni());
		// // TODO: birdhday format?
		// if (aPatient.getBirthdate() != null)
		// params.setParam("birthday", aPatient.getBirthdate().toString());
		// if (aPatient.getSex() != null)
		// params.setParam("sex", aPatient.getSex().toString());

		modelAndView = processRequest(modelAndView, aPatient, pageNumber,
				orderField, orderAscending);

		return modelAndView;
	}

	private ModelAndView processRequest(ModelAndView modelAndView,
			PatientInTrain aPatient, Integer pageNumber, String orderField,
			Boolean orderAscending) {
		List<PatientInTrain> patientList = null;

		// Pagination
		if (pageNumber != null) {
			paginator.setPageIndex(pageNumber);
		}

		// Order
		if (orderField == null || orderAscending == null) {
			orderField = "id";
			orderAscending = true;
		}

		paginator.setOrderAscending(orderAscending);
		paginator.setOrderField(orderField);

		patientList = patientInTrainService.findByExamplePaged(aPatient,
				paginator);

		modelAndView.addObject("patient", new Patient());
		modelAndView.addObject("patientList", patientList);
		modelAndView.addObject("paginator", paginator);
		modelAndView.addObject("params", params);
		modelAndView.addObject("orderField", orderField);
		modelAndView.addObject("orderAscending", orderAscending.toString());

		return modelAndView;
	}

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Date.class, new DateEditor());
		b.registerCustomEditor(PediatricianDiagnosis.class,
				new PediatricianDiagnosisEditor());
	}

	private class DateEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) throws IllegalArgumentException {

			try {
				setValue(simpleDateFormat.parse(text));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public String getAsText() {
			// TODO why its entering here when getValue() == null?
			if (getValue() == null)
				return null;

			return simpleDateFormat.format((Date) getValue());
		}
	}

	private class PediatricianDiagnosisEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			setValue(text);
		}

		@Override
		public String getAsText() {
			// TODO why its entering here when getValue() == null?
			if (getValue() == null)
				return null;

			return simpleDateFormat.format((Date) getValue());
		}
	}

	// Ajax method to get User Personal Queue
	@RequestMapping(value = "/getUserQueue", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	MultiValueMap<String, String> getUserQueue() {
		PatientInTrain example = new PatientInTrain();
		example.setUser((ApplicationUser) getUser());
		List<PatientInTrain> list = patientInTrainService
				.findByExample(example);

		MultiValueMap<String, String> retList = new LinkedMultiValueMap<String, String>();

		for (PatientInTrain pit : list) {
			retList.add(pit.getPatient().getFirstName() + " "
					+ pit.getPatient().getLastName(), pit.getId().toString());
		}

		return retList;
	}

	// Ajax method to get User Personal Queue
	@RequestMapping(value = "/getPatientsInTrain", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	String getPatientsInTrain() {
		Gson gson = new Gson();

		LinkedList<Patient> patients = new LinkedList<Patient>();

		for (PatientInTrain patient : patientInTrainService.findAll()) {
			patients.add(patient.getPatient());
		}

		return gson.toJson(patients);
	}

	@RequestMapping(value = "/getPatientForms", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	String getPatientsForms(@RequestParam Long patientId) {
		Gson gson = new Gson();

		LinkedList<Long> forms = new LinkedList<Long>();

		Patient patient = patientService.findById(patientId);

		if (patient != null) {
			PatientInTrain pit = patientInTrainService.findByPatient(patient);

			forms.add(pit.getSocialworkerform() != null ? pit
					.getSocialworkerform().getId() : null);
			forms.add(pit.getPadiatricianform() != null ? pit
					.getPadiatricianform().getId() : null);
			forms.add(pit.getNurseform() != null ? pit.getNurseform().getId()
					: null);
			forms.add(pit.getDentistform() != null ? pit.getDentistform()
					.getId() : null);
		}

		return gson.toJson(forms);
	}

	@RequestMapping(value = "/getDiagnosis", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	String getDiagnosis(@RequestParam String q) {
		// http://localhost:8081/presentation/getDiagnosis?q=q&limit=150&timestamp=1309723047867
		Gson gson = new Gson();

		PediatricianDiagnosis pd = new PediatricianDiagnosis();
		pd.setName(q);

		Collection<PediatricianDiagnosis> found = pediatricianDiagnosisService
				.findByExample(pd);

		LinkedList<PediatricianDiagnosis> allDiagnosis = new LinkedList<PediatricianDiagnosis>();

		for (PediatricianDiagnosis oneDiag : found)
			allDiagnosis.add(oneDiag);

		// gson.
		return gson.toJson(allDiagnosis);
		// return resultset;
	}

	@RequestMapping(value = "/getCategory", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	String getCategory(@RequestParam String q) {
		List<Item> items = itemService.findByExample(ItemBuilder.createItem()
				.withCategory(q.toLowerCase()).build());

		List<String> categories = new ArrayList<String>();
		for (Item item : items) {
			if (!categories.contains(item.getCategory())) {
				categories.add(item.getCategory());
			}
		}
		return new Gson().toJson(categories);
	}

	@Autowired
	public void setItineraryDAO(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}

	@Autowired
	public void setPatientInTrainService(
			PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@Autowired
	public void setPediatricianDiagnosisService(
			PediatricianDiagnosisService pediatricianDiagnosisService) {
		this.pediatricianDiagnosisService = pediatricianDiagnosisService;
	}

	@Autowired
	public void setOnlineUserService(OnlineUserService onlineUserService) {
		this.onlineUserService = onlineUserService;
	}

	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@Autowired
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
		paginator.setOrderAscending(true);
		paginator.setOrderField("id");
	}

}
