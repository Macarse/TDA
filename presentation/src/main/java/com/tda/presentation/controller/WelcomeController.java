package com.tda.presentation.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.model.patient.Sex;
import com.tda.persistence.paginator.Paginator;
import com.tda.presentation.params.ParamContainer;
import com.tda.service.api.PatientInTrainService;

@Controller
@RequestMapping(value = "/")
@SessionAttributes({ "patient", "user" })
public class WelcomeController {
	private static final String LIST = "welcome/list";

	private PatientInTrainService patientInTrainService;
	private Paginator paginator;
	private ParamContainer params;

	// TODO should be localized?
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	public WelcomeController() {
		params = new ParamContainer();
	}

	@ModelAttribute("sex")
	public Sex[] populateCategories() {
		return Sex.values();
	}

	@ModelAttribute("user")
	public UserDetails getUser() {
		Object aux = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return ((UserDetails) aux);
	}

	@Autowired
	public void setPatientInTrainService(PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@Autowired
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
		paginator.setOrderAscending(true);
		paginator.setOrderField("id");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getList(
			@RequestParam(value = "page", required = false) Integer pageNumber,
			@RequestParam(value = "orderField", required = false) String orderField,
			@RequestParam(value = "orderAscending", required = false) Boolean orderAscending) {
		ModelAndView modelAndView = new ModelAndView(LIST);

		modelAndView = processRequest(modelAndView, new PatientInTrain(), pageNumber,
				orderField, orderAscending);
		
		return modelAndView;
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

//		if (aPatient.getFirstName() != null)
//			params.setParam("firstName", aPatient.getFirstName());
//		if (aPatient.getLastName() != null)
//			params.setParam("lastName", aPatient.getLastName());
//		if (aPatient.getDni() != null)
//			params.setParam("dni", aPatient.getDni());
//		// TODO: birdhday format?
//		if (aPatient.getBirthdate() != null)
//			params.setParam("birthday", aPatient.getBirthdate().toString());
//		if (aPatient.getSex() != null)
//			params.setParam("sex", aPatient.getSex().toString());

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

		patientList = patientInTrainService.findByExamplePaged(aPatient, paginator);

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

}
