package com.tda.model.report;

public class AgeForReport {

	private String age;
	private Integer quantity;

	public AgeForReport(Integer age, Integer quantity) {
		this.age = age.toString();
		this.quantity = quantity;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
