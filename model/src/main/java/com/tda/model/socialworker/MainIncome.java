package com.tda.model.socialworker;

public enum MainIncome {
	WORK_SALARY("Salario por trabajo"), PENSION("Jubilación o pensión"), SUBSIDY(
			"Subsidio o ayuda social del gobierno o iglesias"), OUTSIDE_MONEY(
			"Ayuda en dinero de personas que no viven en el hogar");

	private String description;

	MainIncome(String description) {
		this.setDescription(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
