package com.tda.model.item;


public enum Category {
	misc("Miscelánea"), medical("Medicamentos");

	private String description;

	Category(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}