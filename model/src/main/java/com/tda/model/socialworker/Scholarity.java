package com.tda.model.socialworker;

public enum Scholarity {
	KINDER_GARDEN("Jardín de infantes"), PRIMARY_SCHOOL("Primaria"), HIGH_SCHOOL(
			"Secundaria"), TERCIARY("Terciaria"), DISABLED(
			"Escuela de discapacitados");

	private String description;

	Scholarity(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
