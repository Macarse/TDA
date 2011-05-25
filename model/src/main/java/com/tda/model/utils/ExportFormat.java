package com.tda.model.utils;

public enum ExportFormat {
	PDF("PDF"), XLS("XLS");

	private String description;

	ExportFormat(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
