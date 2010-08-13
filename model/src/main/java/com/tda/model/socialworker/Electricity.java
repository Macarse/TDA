package com.tda.model.socialworker;

public enum Electricity {
	PUBLIC("Público"), PRIVATE("Privado (motor-generador, otros"), NONE(
			"No tiene");

	private String description;

	Electricity(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
