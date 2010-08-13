package com.tda.model.socialworker;

public enum KitchenFuel {
	NETWORK("Gas de red"), BOTTLED("Gas de envasado"), OTHER("Otros combinados");

	private String description;

	KitchenFuel(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
