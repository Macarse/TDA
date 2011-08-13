package com.tda.model.socialworker;

public enum InteriorFloor {
	MOSAIC("Mosaico/baldosa/madera/cer&aacute;mica/alfombra"), CONCRETE(
			"Cemento/ladrillo fijo"), BRICKS("Ladrillo suelto/tierra");

	private String description;

	InteriorFloor(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
