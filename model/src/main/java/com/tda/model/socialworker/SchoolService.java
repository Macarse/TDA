package com.tda.model.socialworker;

public enum SchoolService {
	DINING_ROOM("Comedor"), BREAKFAST("Desayunador/Copa de leche"), NONE(
			"Ninguno");

	private String description;

	SchoolService(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
