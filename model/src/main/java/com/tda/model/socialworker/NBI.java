package com.tda.model.socialworker;

public enum NBI {
	OVERCROWDING("Hacinamiento"), LIVING_PLACE("Vivienda"), HEALTH_CONDITIONS(
			"Condiciones sanitarias"), SCHOOL_ATTENDANCE("Asistencia escolar"), SUBSISTENCE_CAPACITY(
			"Capacidad de subsistencia");

	private String description;

	NBI(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
