package com.tda.model.report;

public class PrevalentDiagnosticForDestinationReport {

	private String destination;
	private String diagnostic;
	private Integer quantity;

	public PrevalentDiagnosticForDestinationReport() {

	}

	public PrevalentDiagnosticForDestinationReport(String destination,
			String diagnostic, int quantity) {
		this.destination = (destination != null && !destination.isEmpty() ? destination
				: "Destino sin definir");
		this.diagnostic = (diagnostic != null && !diagnostic.isEmpty() ? diagnostic
				: "Diagnóstico sin definir");
		this.quantity = quantity;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
