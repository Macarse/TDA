package com.tda.model.socialworker;

public enum RoofType {
	MEMBRANE("Membrana"), TILE("Baldosa/losa sin cubierta"), SLATE(
			"Pizarra/teja"), METAL_SHEET("Chapa de metal"), PLASTIC(
			"Chapa de fibrocemento/plástico"), CARDBOARD("Chapa de cartón"), STRAW(
			"Caña/tabla/paja con barro/paja sola");

	private String description;

	RoofType(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
