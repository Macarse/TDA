package com.tda.model.pediatrician;

public enum ExitStatus {
	HEALTHY("Sano"), PATHOLOGY("Con patolog&iacute;a");

	private String description;

	ExitStatus(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
