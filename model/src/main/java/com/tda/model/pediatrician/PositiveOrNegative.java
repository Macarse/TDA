package com.tda.model.pediatrician;

public enum PositiveOrNegative {
	POSITIVE("Positivo"), NEGATIVE("Negativo");

	private String description;

	PositiveOrNegative(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
