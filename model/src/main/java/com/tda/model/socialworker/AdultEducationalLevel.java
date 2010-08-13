package com.tda.model.socialworker;

public enum AdultEducationalLevel {
	PRIMARY_UNFINISHED("Primaria incompleta"), PRIMARY_FINISHED(
			"Primaria completa"), HIGH_SCHOOL_UNFINISHED(
			"Secundaria incompleta"), HIGH_SCHOOL_FINISHED(
			"Secundaria completa"), TERCIARY_UNFINISHED("Terciario incompleto"), TERCIARY_FINISHED(
			"Terciario completo");

	private String description;

	AdultEducationalLevel(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
