package com.tda.model.pediatrician;

public enum BirthType {
	UNCOMPLICATED("Eutócico"), DYSTOCIC("Distócico");

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
