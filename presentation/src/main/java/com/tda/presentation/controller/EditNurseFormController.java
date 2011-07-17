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

import com.tda.model.nurse.NurseForm;
import com.tda.model.utils.FormType;
import com.tda.service.api.NurseFormService;
import com.tda.service.api.PatientInTrainService;

@Controller
@RequestMapping("/patient/*/nurse/{nurseFormId}/edit")
@SessionAttributes("nurseForm")
public class EditNurseFormController extends BaseNurseFormController {
	private static final String NURSE_FORM = "nurseform/form";
	private NurseFormService nurseFormService;
	private PatientInTrainService patientInTrainService;

	@Autowired
	public void setPatientInTrainService(
			PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("nurseFormId") long nurseFormId,
			Model model) {
		NurseForm nurseForm = nurseFormService.findById(nurseFormId);
		model.addAttribute("nurseForm", nurseForm);

		boolean editable = patientInTrainService.isActiveForm(nurseFormId,
				FormType.nurse);

		model.addAttribute("editable", editable);
		return NURSE_FORM;
	}

	@RequestMapping(method = { RequestMethod.POST })
	public String processSubmit(
			@Valid @ModelAttribute("nurseForm") NurseForm nurseForm,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return NURSE_FORM;
		} else {
			nurseFormService.update(nurseForm);
			// status.setComplete();
			return "redirect:/";
		}
	}

	@Autowired
	public void setNurseFormService(NurseFormService nurseFormService) {
		this.nurseFormService = nurseFormService;
	}
}
