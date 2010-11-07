package com.tda.presentation.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tda.model.pediatrician.BirthPlace;
import com.tda.model.pediatrician.BirthType;
import com.tda.model.pediatrician.BirthTypePresentation;
import com.tda.model.pediatrician.BirthTypeTermination;
import com.tda.model.pediatrician.ExitStatus;
import com.tda.model.pediatrician.PositiveNegativeOrUndefined;
import com.tda.model.pediatrician.PositiveOrNegative;
import com.tda.model.pediatrician.SueroType;

public class BasePediatricianFormController {
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("birthPlace")
	public BirthPlace[] populateBirthPlace() {
		return BirthPlace.values();
	}

	@ModelAttribute("testEmbarazo")
	public PositiveOrNegative[] populateTestEmbarazo() {
		return PositiveOrNegative.values();
	}

	@ModelAttribute("streptococos")
	public PositiveOrNegative[] populateStreptococos() {
		return PositiveOrNegative.values();
	}

	@ModelAttribute("testGraham")
	public PositiveOrNegative[] populateTestGraham() {
		return PositiveOrNegative.values();
	}

	@ModelAttribute("chagas")
	public PositiveNegativeOrUndefined[] populateChagas() {
		return PositiveNegativeOrUndefined.values();
	}

	@ModelAttribute("suero")
	public SueroType[] populateSueroType() {
		return SueroType.values();
	}

	@ModelAttribute("birthType")
	public BirthType[] populateBirthType() {
		return BirthType.values();
	}

	@ModelAttribute("birthTypePresentation")
	public BirthTypePresentation[] populateBirthTypePresentation() {
		return BirthTypePresentation.values();
	}

	@ModelAttribute("birthTypeTermination")
	public BirthTypeTermination[] populateBirthTypeTermination() {
		return BirthTypeTermination.values();
	}

	@ModelAttribute("exitStatus")
	public ExitStatus[] populateExitStatus() {
		return ExitStatus.values();
	}
}
