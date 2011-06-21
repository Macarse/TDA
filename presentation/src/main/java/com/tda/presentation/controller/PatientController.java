package com.tda.presentation.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.model.patient.Sex;
import com.tda.model.utils.FormContainer;
import com.tda.model.utils.FormType;
import com.tda.persistence.paginator.Paginator;
import com.tda.presentation.params.ParamContainer;
import com.tda.service.api.FormContainerService;
import com.tda.service.api.PatientInTrainService;
import com.tda.service.api.PatientService;

@Controller
@RequestMapping(value = "/patient")
@SessionAttributes("patient")
public class PatientController {
	private static final String FORM_DELETE_ERROR = "form.deleteError";
	private static final String FORM_NOT_FOUND = "form.notFound";
	private static final String FORM_MESSAGE = "message";
	private static final String FORM_ADD_SUCCESSFUL = "form.addSuccessful";
	private static final String REDIRECT_TO_LIST = "redirect:/patient/";
	private static final String CREATE_FORM = "patient/createForm";
	private static final String LIST = "patient/list";
	private static final String HISTORY_LIST = "patient/historylist";

	private PatientService patientService;
	private PatientInTrainService patientInTrainService;
	private FormContainerService formContainerService;
	private Paginator paginator;
	private ParamContainer params;

	@Autowired
	public void setPatientInTrainService(
			PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@Autowired
	public void setFormContainerService(
			FormContainerService formContainerService) {
		this.formContainerService = formContainerService;
	}

	public PatientController() {
		params = new ParamContainer();
	}

	@ModelAttribute("sex")
	public Sex[] populateCategories() {
		return Sex.values();
	}

	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
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

		// Pagination
		if (pageNumber != null) {
			paginator.setPageIndex(pageNumber);
		} else {
			paginator.setPageIndex(1);
		}

		// Order
		if (orderField == null || orderAscending == null) {
			orderField = "id";
			orderAscending = true;
		}

		paginator.setOrderAscending(orderAscending);
		paginator.setOrderField(orderField);

		ModelAndView modelAndView = new ModelAndView(LIST);

		modelAndView = processRequest(modelAndView, new Patient(), pageNumber,
				orderField, orderAscending);

		return modelAndView;
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView getList(
			@ModelAttribute Patient aPatient,
			BindingResult result,
			@RequestParam(value = "page", required = false) Integer pageNumber,
			@RequestParam(value = "orderField", required = false) String orderField,
			@RequestParam(value = "orderAscending", required = false) Boolean orderAscending) {

		ModelAndView modelAndView = new ModelAndView(LIST);

		// set first page paginator
		paginator.setPageIndex(1);

		if (aPatient.getFirstName() != null)
			params.setParam("firstName", aPatient.getFirstName());
		if (aPatient.getLastName() != null)
			params.setParam("lastName", aPatient.getLastName());
		if (aPatient.getDni() != null)
			params.setParam("dni", aPatient.getDni());
		// TODO: birdhday format?
		if (aPatient.getBirthdate() != null)
			params.setParam("birthday", aPatient.getBirthdate().toString());
		if (aPatient.getSex() != null)
			params.setParam("sex", aPatient.getSex().toString());

		modelAndView = processRequest(modelAndView, aPatient, pageNumber,
				orderField, orderAscending);

		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute("patient", new Patient());

		return CREATE_FORM;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(Model model,
			@Valid @ModelAttribute Patient aPatient, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();

		/* Check DNI uniqueness */
		List<Patient> patientsWithSameDni = patientService.findByDni(aPatient
				.getDni());

		if (!patientsWithSameDni.isEmpty()
				&& !patientsWithSameDni.get(0).getId().equals(aPatient.getId())) {
			FieldError error = new FieldError("patient", "dni",
					aPatient.getDni(), false,
					new String[] { "error.dni.duplicated" },
					new Object[] { "DNI duplicado" }, "DNI duplicado");
			result.addError(error);
		}

		// TODO if we're editing and not adding a new item the message
		// seems somewhat... misleading, CHANGE IT :D
		if (result.hasErrors()) {
			modelAndView.setViewName(CREATE_FORM);
		} else {
			patientService.save(aPatient);
			modelAndView.setViewName(REDIRECT_TO_LIST);
			modelAndView.addObject(FORM_MESSAGE, FORM_ADD_SUCCESSFUL);

		}

		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		Patient patient = patientService.findById(id);
		model.addAttribute("patient", patient);

		return CREATE_FORM;
	}

	@RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
	public ModelAndView getPatientHistory(
			@PathVariable Long id,
			Model model,
			@RequestParam(value = "page", required = false) Integer pageNumber,
			@RequestParam(value = "orderField", required = false) String orderField,
			@RequestParam(value = "orderAscending", required = false) Boolean orderAscending) {

		// getPatient
		Patient patient = patientService.findById(id);

		// Pagination
		if (pageNumber != null) {
			paginator.setPageIndex(pageNumber);
		} else {
			paginator.setPageIndex(1);
		}

		// Order
		if (orderField == null || orderAscending == null) {
			orderField = "fillingDate";
			orderAscending = true;
		}

		paginator.setOrderAscending(orderAscending);
		paginator.setOrderField(orderField);

		FormContainer example = new FormContainer();
		example.setPatient(patient);
		List<FormContainer> patientforms = formContainerService
				.findByExamplePaged(example, paginator);

		ModelAndView modelAndView = new ModelAndView(HISTORY_LIST);
		modelAndView.addObject("formlist", patientforms);
		modelAndView.addObject("paginator", paginator);
		modelAndView.addObject("params", params);
		modelAndView.addObject("orderField", orderField);
		modelAndView.addObject("orderAscending", orderAscending.toString());
		modelAndView.addObject("patient", patient);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ModelAndView deleteItem(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView(REDIRECT_TO_LIST);
		Patient aPatient = patientService.findById(id);

		if (aPatient == null) {
			modelAndView.addObject(FORM_MESSAGE, FORM_NOT_FOUND);
		} else {

			try {
				patientService.delete(aPatient);
			} catch (Exception e) {
				modelAndView.addObject(FORM_MESSAGE, FORM_DELETE_ERROR);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{patient_id}/{formType}/{id}", method = RequestMethod.POST)
	public ModelAndView deleteForm(@PathVariable Long patient_id,
			@PathVariable FormType formType, @PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/patient/history/" + patient_id + "?");

		try {
			Patient aPatient = patientService.findById(patient_id);

			if (aPatient == null) {
				modelAndView.addObject(FORM_MESSAGE, FORM_NOT_FOUND);
			} else {
				PatientInTrain pit = patientInTrainService
						.findByPatient(aPatient);
				if (pit != null) {
					switch (formType) {
					case dentist:
						if (pit.getDentistform() != null
								&& pit.getDentistform().getId().equals(id))
							pit.setDentistform(null);
						break;
					case nurse:
						if (pit.getNurseform() != null
								&& pit.getNurseform().getId().equals(id))
							pit.setNurseform(null);
						break;
					case pediatrician:
						if (pit.getPadiatricianform() != null
								&& pit.getPadiatricianform().getId().equals(id))
							pit.setPadiatricianform(null);
						break;
					case socialworker:
						if (pit.getSocialworkerform() != null
								&& pit.getSocialworkerform().getId().equals(id))
							pit.setSocialworkerform(null);
						break;
					}
					patientInTrainService.update(pit);
				}
			}

			formContainerService.delete(formType, id);
		} catch (Exception e) {
			modelAndView.addObject(FORM_MESSAGE, FORM_NOT_FOUND);
		}

		return modelAndView;
	}

	private ModelAndView processRequest(ModelAndView modelAndView,
			Patient aPatient, Integer pageNumber, String orderField,
			Boolean orderAscending) {
		List<Patient> patientList = null;

		// Pagination
		if (pageNumber != null) {
			paginator.setPageIndex(pageNumber);
		}

		// Order
		if (orderField == null || orderAscending == null) {
			orderField = "firstName";
			orderAscending = true;
		}

		paginator.setOrderAscending(orderAscending);
		paginator.setOrderField(orderField);

		patientList = patientService.findByExamplePaged(aPatient, paginator);

		boolean[] patientInTrainArray = new boolean[patientList.size()];
		int i = 0;
		for (Patient patient : patientList)
			patientInTrainArray[i++] = patientInTrainService.isInTrain(patient);

		modelAndView.addObject("patientInTrainArray", patientInTrainArray);

		modelAndView.addObject("patient", new Patient());
		modelAndView.addObject("patientList", patientList);
		modelAndView.addObject("paginator", paginator);
		modelAndView.addObject("params", params);
		modelAndView.addObject("orderField", orderField);
		modelAndView.addObject("orderAscending", orderAscending.toString());

		return modelAndView;
	}
	
	@RequestMapping(value = "/getPicture/{id}", method = RequestMethod.GET)
	public String getPicture(@PathVariable long id, HttpServletResponse response, HttpServletRequest request){
		Patient aPatient = patientService.findById(id);
		
		String photoPath = request.getSession().getServletContext()
			.getRealPath("themes/default/image/img-not-found.jpg");
		
		try{
			response.setContentType("image/jpeg");
			OutputStream os = response.getOutputStream();
			if (aPatient.getImage() == null){
				BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(photoPath)));
				int read = 0;
				byte[] buffer = new byte[8192];
				
				while ((read = is.read(buffer, 0 , 8192)) != -1) {
					os.write(buffer,0, read);
  		        }
				
				is.close();
			}else{
				os.write(aPatient.getImage());
			}
			os.flush();
			os.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/takePicture/{id}", method = RequestMethod.GET)
	public ModelAndView takePicture(@PathVariable long id){
		ModelAndView modelAndView = new ModelAndView("patient/takePicture");
		
		Patient aPatient = patientService.findById(id);

		if (aPatient == null) {
			modelAndView.addObject(FORM_MESSAGE, FORM_NOT_FOUND);
		} else {
			modelAndView.addObject("patient", aPatient);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/takePicture", method = RequestMethod.POST)
	public @ResponseBody
	String takePicture(HttpServletRequest request){
		try {
			InputStream inputStream = request.getInputStream();
			
			int readBytes = 0;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int pos = 0;
			
			long patientId = Long.parseLong(request.getParameter("id"));
			while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
				out.write(buffer,0,readBytes);
				pos += readBytes;
			}
			
			Patient aPatient = patientService.findById((long)patientId);
			aPatient.setImage(out.toByteArray());
			patientService.save(aPatient);
			
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
}
