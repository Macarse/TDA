package com.tda.model.report;

public class AgeForDestinationReport {

	private String destination;
	private String age;
	private Integer quantity;

	public AgeForDestinationReport() {

	}

	public AgeForDestinationReport(String destination, String age,
			Integer quantity) {
		this.destination = destination;
		this.age = age;
		this.quantity = quantity;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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
