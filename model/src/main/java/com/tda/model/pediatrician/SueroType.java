package com.tda.model.pediatrician;

public enum SueroType {
	ANICTERICO("Anict&eacute;rico"), ICTERICO("Ict&eacute;rico"), OPALESCENTE("Opalescente"), TURBIO(
			"Turbio"), LECHOSO("Lechoso");

	private String description;

	SueroType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
