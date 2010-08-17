package com.tda.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.item.Item;
import com.tda.model.patient.Patient;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.ItemService;
import com.tda.service.api.PatientService;

@Controller
@RequestMapping(value = "/patient")
@SessionAttributes("patient")
public class PatientController {
	private static final String PATIENT_FORM_DELETE_ERROR = "patient.form.deleteError";
	private static final String PATIENT_FORM_NOT_FOUND = "patient.form.notFound";
	private static final String PATIENT_FORM_MESSAGE = "message";
	private static final String PATIENT_FORM_ADD_SUCCESSFUL = "patient.form.addSuccessful";
	private static final String REDIRECT_TO_PATIENT_LIST = "redirect:/patient/";
	private static final String PATIENT_CREATE_FORM = "patient/createForm";
	private static final String PATIENT_LIST = "patient/list";
	private static final String PATIENT_LIST_SEARCH = "patient/search";
	
	private PatientService patientService;
	private Paginator paginator;
	
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
		ModelAndView modelAndView = new ModelAndView(PATIENT_LIST);

		List<Patient> patientList = null;

		// Pagination
		if (pageNumber != null) {
			paginator.setPageIndex(pageNumber);
		}

		// Order
		if (orderField != null && orderAscending != null) {
			paginator.setOrderAscending(orderAscending);
			paginator.setOrderField(orderField);
			paginator.setParam("orderField", orderField);
			paginator.setParam("orderAscending", orderAscending.toString());
		}

		patientList = patientService.findAllPaged(paginator);
		modelAndView.addObject("patient", new Patient());
		modelAndView.addObject("patientList", patientList);
		modelAndView.addObject("paginator", paginator);

		return modelAndView;
	}

}
