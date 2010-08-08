package com.tda.persistence.paginator;

public enum Order {
	asc("Ascendente"), desc("Descendente");

	private String description;

	Order(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
