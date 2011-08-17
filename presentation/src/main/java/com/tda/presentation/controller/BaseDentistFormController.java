package com.tda.presentation.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tda.model.dentist.DentistAction;
import com.tda.model.dentist.SeverityLevel;

public class BaseDentistFormController extends CommonController {

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("dentistAction")
	public DentistAction[] populateDentistAction() {
		return DentistAction.values();
	}

	@ModelAttribute("periodontalDisease")
	public SeverityLevel[] populatePeriodontalDisease() {
		return SeverityLevel.values();
	}

	@ModelAttribute("gumDisease")
	public SeverityLevel[] populateGumDisease() {
		return SeverityLevel.values();
	}

	@ModelAttribute("toothMobility")
	public SeverityLevel[] populateToothMobility() {
		return SeverityLevel.values();
	}
}
