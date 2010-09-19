package com.tda.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tda.model.dentist.DentistForm;
import com.tda.model.patient.Patient;
import com.tda.service.api.DentistFormService;
import com.tda.service.api.PatientService;

@Controller
@RequestMapping(value = "/patient/{patientId}/dentist/new")
@SessionAttributes("dentistForm")
public class AddDentistFormController extends BaseDentistFormController {
	private static final String DENTIST_ADD_FORM = "dentistform/form";
	private static final String REDIRECT_AFTER_SAVE = "redirect:/welcome/";

	private DentistFormService DentistFormService;
	private PatientService patientService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("patientId") long patientId,
			Model model) {
		Patient patient = patientService.findById(patientId);
		DentistForm dentistForm = new DentistForm();
		dentistForm.setPatient(patient);
		model.addAttribute("dentistForm", dentistForm);

		return DENTIST_ADD_FORM;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@Valid @ModelAttribute("dentistForm") DentistForm dentistForm,
			BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			return DENTIST_ADD_FORM;
		} else {
			DentistFormService.save(dentistForm);
			status.setComplete();
			return REDIRECT_AFTER_SAVE;
		}
	}

	@Autowired
	public void setDentistFormService(DentistFormService dentistFormService) {
		this.DentistFormService = dentistFormService;
	}

	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
}
