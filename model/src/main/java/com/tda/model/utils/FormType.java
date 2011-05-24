package com.tda.model.utils;

public enum FormType {
	Pediatrician("Pediatra"), SocialWorker("TrabajadorSocial"), Nurse(
			"Enfermero"), Dentist("Dentista");

	private String description;

	FormType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
