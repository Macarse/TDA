package com.tda.model.pediatrician;

public enum BirthTypePresentation {
	BREECH("Pod&aacute;lica"), TRANSVERSE("Transversa");

	private String description;

	BirthTypePresentation(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
