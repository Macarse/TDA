package com.tda.model.socialworker;

public enum Mistreatment {
	PHYSICAL("Físico"), PSYCHOLOGICAL("Psicológico");

	private String description;

	Mistreatment(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
