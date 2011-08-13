package com.tda.model.socialworker;

public enum WaterSourceType {
	PUBLIC("Red p&uacute;blica (agua corriente)"), OTHER("Otros");

	private String description;

	WaterSourceType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
