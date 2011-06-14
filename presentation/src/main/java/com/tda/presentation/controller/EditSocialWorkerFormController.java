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

import com.tda.model.socialworker.SocialWorkerForm;
import com.tda.model.utils.FormType;
import com.tda.service.api.PatientInTrainService;
import com.tda.service.api.SocialWorkerFormService;

@Controller
@RequestMapping("/patient/*/socialworker/{socialWorkerFormId}/edit")
@SessionAttributes("socialWorkerForm")
public class EditSocialWorkerFormController extends
		BaseSocialWorkerFormController {
	private static final String SOCIAL_WORKER_FORM = "socialworkerform/form";
	private SocialWorkerFormService socialWorkerFormService;
	private PatientInTrainService patientInTrainService;

	@Autowired
	public void setPatientInTrainService(
			PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable("socialWorkerFormId") long socialWorkerFormId,
			Model model) {
		SocialWorkerForm socialWorkerForm = socialWorkerFormService
				.findById(socialWorkerFormId);
		model.addAttribute("socialWorkerForm", socialWorkerForm);

		boolean editable = patientInTrainService.isActiveForm(
				socialWorkerFormId, FormType.socialworker);

		model.addAttribute("editable", editable);

		return SOCIAL_WORKER_FORM;
	}

	@RequestMapping(method = { RequestMethod.POST })
	public String processSubmit(
			@Valid @ModelAttribute("socialWorkerForm") SocialWorkerForm socialWorkerForm,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return SOCIAL_WORKER_FORM;
		} else {
			socialWorkerFormService.update(socialWorkerForm);
			// status.setComplete();
			return "redirect:/";
		}
		// model.addAttribute("socialWorkerForm", socialWorkerForm);
	}

	@Autowired
	public void setSocialWorkerFormService(
			SocialWorkerFormService socialWorkerFormService) {
		this.socialWorkerFormService = socialWorkerFormService;
	}
}
