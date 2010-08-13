package com.tda.model.socialworker;

public enum WaterSourceType {
	PUBLIC("Red pública (agua corriente)"), OTHER("Otros");

	private String description;

	WaterSourceType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
