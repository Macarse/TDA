package com.tda.model.socialworker;

public enum BathroomSewerType {
	PUBLIC("A red pública (cloaca)"), CESSPIT("A pozo ciego"), EARTH_HOLE(
			"A hoyo/excavación en la tierra");

	private String description;

	BathroomSewerType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
