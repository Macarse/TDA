package com.tda.presentation.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tda.model.dentist.DentistAction;

public class BaseDentistFormController {

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("dentistAction")
	public DentistAction[] populateNurseAction() {
		return DentistAction.values();
	}
}
