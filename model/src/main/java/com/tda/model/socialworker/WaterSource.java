package com.tda.model.socialworker;

public enum WaterSource {

	PIPES("Por cañería dentro de la vivienda"), OUTSIDE_LIVING_PLACE(
			"Fuera de la vivienda pero adentro del terreno"), OUTSIDE_TERRAIN(
			"Fuera del terreno");

	private String description;

	WaterSource(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
