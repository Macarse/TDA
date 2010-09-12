package com.tda.model.nurse;

import javax.persistence.Transient;

public enum NurseAction {
	GIVE_MEDICINE("Entrega de medicamentos"), PERFORM_CURATION("Curaci—n"), PERFORM_SUTURE(
			"Sutura"), BURN("Quemadura"), SPRAY_THERAPY("Aerosol Terapia"), NEBULIZATIONS(
			"Nebulizaciones");

	private String description;

	NurseAction(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public String getDescription() {
		return description;
	}
}
