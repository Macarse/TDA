package com.tda.presentation.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tda.model.nurse.NurseAction;
import com.tda.model.nurse.NurseForm;
import com.tda.model.nurse.Vaxine;
import com.tda.model.patient.Patient;
import com.tda.service.api.NurseFormService;
import com.tda.service.api.PatientService;
import com.tda.service.api.VaxineService;

@Controller
@RequestMapping(value = "/patient/{patientId}/nurse/new")
@SessionAttributes("nurseForm")
public class AddNurseFormController extends BaseNurseFormController {
	private static final String NURSE_ADD_FORM = "nurseform/form";
	private static final String REDIRECT_AFTER_SAVE = "redirect:/";

	private NurseFormService nurseFormService;
	private PatientService patientService;
	private VaxineService vaxineService;

	@ModelAttribute("allVaxines")
	public List<Vaxine> populateVaxines() {
		return vaxineService.findAll();
	}

	@ModelAttribute("allNurseActions")
	public NurseAction[] populateNurseActions() {
		return NurseAction.values();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("patientId") long patientId,
			Model model) {
		Patient patient = patientService.findById(patientId);
		NurseForm nurseForm = new NurseForm();
		nurseForm.setPatient(patient);
		model.addAttribute("nurseForm", nurseForm);

		return NURSE_ADD_FORM;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@Valid @ModelAttribute("nurseForm") NurseForm nurseForm,
			BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			return NURSE_ADD_FORM;
		} else {
			nurseFormService.save(nurseForm);
			status.setComplete();
			return REDIRECT_AFTER_SAVE;
		}
	}

	@Autowired
	public void setNurseFormService(NurseFormService nurseFormService) {
		this.nurseFormService = nurseFormService;
	}

	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@Autowired
	public void setVaxineService(VaxineService vaxineService) {
		this.vaxineService = vaxineService;
	}

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Vaxine.class, new VaxineEditor());
	}

	private class VaxineEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			// En text me llega el ID de la VAXINE en cuestion
			setValue(vaxineService.findById(Long.parseLong(text)));
		}

		@Override
		public String getAsText() {
			return ((Vaxine) getValue()).getName();
		}
	}
}
