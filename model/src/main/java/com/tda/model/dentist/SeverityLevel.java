package com.tda.model.dentist;

import javax.persistence.Transient;

public enum SeverityLevel {
	LOW("Leve"), MEDIUM("Moderada"), HIGH("Severa");

	private String description;

	private SeverityLevel(String description) {
		this.description = description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public String getDescription() {
		return description;
	}
}
