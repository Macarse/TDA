package com.tda.model.pediatrician;

public enum PositiveNegativeOrUndefined {
	POSITIVE("Positivo"), NEGATIVE("Negativo"), UNDEFINED("Indeterminado");

	private String description;

	private PositiveNegativeOrUndefined(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
