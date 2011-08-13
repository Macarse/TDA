package com.tda.model.socialworker;

public enum BathroomSewerType {
	PUBLIC("A red p&uacute;blica (cloaca)"), CESSPIT("A pozo ciego"), EARTH_HOLE(
			"A hoyo/excavaci&oacute;n en la tierra");

	private String description;

	BathroomSewerType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
