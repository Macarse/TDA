package com.tda.model.pediatrician;

public enum SueroType {
	ANICTERICO("Anictérico"), ICTERICO("Ictérico"), OPALESCENTE("Opalescente"), TURBIO(
			"Turbio"), LECHOSO("Lechoso");

	private String description;

	SueroType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
