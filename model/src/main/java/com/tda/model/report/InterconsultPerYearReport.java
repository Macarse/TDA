package com.tda.model.report;

public class InterconsultPerYearReport {

	private String year;
	private Integer quantity;

	public InterconsultPerYearReport() {

	}

	public InterconsultPerYearReport(String year, int quantity) {
		this.year = (year != null && !year.isEmpty() ? year : "Año sin definir");
		this.quantity = quantity;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
