package com.tda.service.report;

import java.util.Date;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ChartBuilderException;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;

public class SexGraphReportLayout {

	@SuppressWarnings({ "deprecation" })
	public DynamicReport buildReportLayout() throws ColumnBuilderException,
			ClassNotFoundException, ChartBuilderException {

		FastReportBuilder drb = new FastReportBuilder();

		drb.addColumn("Id", "id", Long.class.getName(), 50)
				.addColumn("DNI", "dni", String.class.getName(), 50)
				.addColumn("Nombre", "firstName", String.class.getName(), 50)
				.addColumn("Apellido", "lastName", String.class.getName(), 50)
				.addColumn("Sexo", "sexString", String.class.getName(), 50)
				.addColumn("Fecha de nacimiento", "birthdate",
						Date.class.getName(), 50).setPrintColumnNames(true)

				// Disables pagination
				.setIgnorePagination(true)

				// Experiment with this numbers to see the effect
				.setMargins(0, 0, 0, 0)

				// Set the title shown inside the Excel file
				.setTitle("Reporte de pacientes")

				// Set the subtitle shown inside the Excel file
				.setSubtitle("Este reporte fue generado en " + new Date())

				// Set to true if you want to constrain your report within the
				// page boundaries
				// For longer reports, set it to false
				.setUseFullPageWidth(true);

		// Set the name of the file
		drb.setReportName("Reporte de Pacientes");

		// Build the report layout
		// Note this just the layout. It doesn't have any data yet!
		DynamicReport dr = drb.build();

		// Return the layout
		return dr;
	}
}