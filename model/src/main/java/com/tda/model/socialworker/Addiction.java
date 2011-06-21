package com.tda.model.socialworker;

public enum Addiction {
	ALCOHOLISM("Alcoholismo"), SMOKING("Tabaquismo"), LEGAL_DRUGS(
			"Drogas legales"), ILLEGAL_DRUGS("Drogas ilegales");

	private String description;

	Addiction(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
