package com.tda.model.pediatrician;

public enum BirthType {
	UNCOMPLICATED("Eut&oacute;cico"),
	DYSTOCIC("Dist&oacute;cico");

	private String description;

	BirthType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
