package com.tda.model.pediatrician;

public enum BirthTypeTermination {
	SPONTANEOUS("Espontánea"), FORCEPS("Fórceps"), VACUUM("Vacuum"), CAESAREAN(
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
