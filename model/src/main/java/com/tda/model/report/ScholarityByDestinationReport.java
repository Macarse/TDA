package com.tda.model.report;

public class ScholarityByDestinationReport {

	private String destination;
	private String scholarity;
	private Integer quantity;

	public ScholarityByDestinationReport() {
	}

	public ScholarityByDestinationReport(String destination, String scholarity,
			int quantity) {
		this.destination = (destination != null && !destination.isEmpty() ? destination
				: "Destino sin definir");
		this.scholarity = (scholarity != null && !scholarity.isEmpty() ? scholarity
				: "Escolaridad sin definir");
		this.quantity = quantity;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getScholarity() {
		return scholarity;
	}

	public void setScholarity(String scholarity) {
		this.scholarity = scholarity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
