package com.tda.model.report;

import com.tda.model.socialworker.NBI;

public class NbiForDestinationReport {

	private String destination;
	private String nbi;
	private Integer quantity;

	public NbiForDestinationReport() {

	}

	public NbiForDestinationReport(String destination, String nbi,
			Integer quantity) {
		this.destination = (destination != null && !destination.isEmpty() ? destination
				: "Destino sin definir");
		this.nbi = (nbi != null && !nbi.isEmpty() ? NBI.valueOf(nbi)
				.getDescription() : "NBI sin definir");
		this.quantity = quantity;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getNbi() {
		return nbi;
	}

	public void setNbi(String nbi) {
		this.nbi = nbi;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
