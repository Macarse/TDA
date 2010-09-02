package com.tda.model.pediatrician;

public enum BirthPlace {
	HOSPITAL("Hospital"), HOME("Domicilio"), OTHER("Otros");

	private String description;

	BirthPlace(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
