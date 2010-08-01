package com.tda.model.item;


public enum MeasureUnit {
	mts("Metros"), lts("Litros"), unit("Unidades");
	
	private String description;

	MeasureUnit(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}