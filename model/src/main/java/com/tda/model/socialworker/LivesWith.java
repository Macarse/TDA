package com.tda.model.socialworker;

public enum LivesWith {
	PARENTS("Padres"), SINGLE_PARENT("Padre/Madre solo"), 
	GRAND_PARENTS("Abuelos"), 
	UNCLES("T&iacute;os"), 
	BROTHERS("Hermanos"), 
	FIANCEE("Pareja"), 
	SONS("Hijo/s"),
	OTHER("Otros");

	private String description;

	LivesWith(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
