package com.tda.model.socialworker;

public enum SchoolHours {
	MORNING("Por la ma&ntilde;ana"), AFTERNOON("Por la tarde"), DOUBLE(
			"Ma&ntilde;ana y tarde"), WARD("Permanece en la escuela durante la semana");

	private String description;

	SchoolHours(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
