package com.tda.model.dentist;

import javax.persistence.Transient;

public enum DentistAction {
	GIVE_MEDICINE("Entrega de medicamentos");

	private String description;

	DentistAction(String description) {
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
