package com.tda.model.patient;

public enum Sex {
	male("Masculino"), female("Femenino");

	private String description;

	Sex(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
