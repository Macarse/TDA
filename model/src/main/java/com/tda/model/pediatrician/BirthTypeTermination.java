package com.tda.model.pediatrician;

public enum BirthTypeTermination {
	SPONTANEOUS("Espont&aacute;nea"), FORCEPS("F&oacute;rceps"), VACUUM("Vacuum"), CAESAREAN(
			"Cesárea");

	private String description;

	BirthTypeTermination(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
