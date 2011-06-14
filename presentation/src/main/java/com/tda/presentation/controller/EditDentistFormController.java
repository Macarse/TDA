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
import com.tda.model.utils.FormType;
import com.tda.service.api.DentistFormService;
import com.tda.service.api.PatientInTrainService;

@Controller
@RequestMapping("/patient/*/dentist/{dentistFormId}/edit")
@SessionAttributes("dentistForm")
public class EditDentistFormController extends BaseDentistFormController {
	private static final String NURSE_FORM = "dentistform/form";
	private DentistFormService dentistFormService;
	private PatientInTrainService patientInTrainService;

	@Autowired
	public void setPatientInTrainService(
			PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("dentistFormId") long dentistFormId,
			Model model) {
		DentistForm dentistForm = dentistFormService.findById(dentistFormId);
		model.addAttribute("dentistForm", dentistForm);

		boolean editable = patientInTrainService.isActiveForm(dentistFormId,
				FormType.dentist);

		model.addAttribute("editable", editable);

		return NURSE_FORM;
	}

	@RequestMapping(method = { RequestMethod.POST })
	public String processSubmit(
			@Valid @ModelAttribute("dentistForm") DentistForm dentistForm,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return NURSE_FORM;
		} else {
			dentistFormService.update(dentistForm);
			// status.setComplete();
			return "redirect:/";
		}
	}

	@Autowired
	public void setDentistFormService(DentistFormService dentistFormService) {
		this.dentistFormService = dentistFormService;
	}
}
