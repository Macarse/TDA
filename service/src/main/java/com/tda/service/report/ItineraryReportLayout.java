package com.tda.service.report;

import java.util.Date;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;

/**
 * Everything under the package org.krams.tutorial.dynamicjasper are settings
 * imposed by DynamicJasper (not Jasper)
 * <p>
 * 
 * Builds the report layout, the template, the design, the pattern or whatever
 * synonym you may want to call it.
 */
public class ItineraryReportLayout {

	/**
	 * Builds the report layout. This doesn't have any data yet. This is your
	 * template.
	 * 
	 * @return the layout
	 */
	public DynamicReport buildReportLayout() throws ColumnBuilderException,
			ClassNotFoundException {

		FastReportBuilder drb = new FastReportBuilder();

		drb.addColumn("Id", "id", Long.class.getName(), 50)
				.addColumn("Fecha inicio", "beginningDate",
						Date.class.getName(), 50)
				.addColumn("Fecha fin", "endDate", Date.class.getName(), 50)
				.addColumn("Ciudades visitados", "placesStr",
						String.class.getName(), 50)
				.addColumn("Cantidad de pacientes atendidos",
						"attendedPatients", Integer.class.getName(), 50)
				.setPrintColumnNames(true)

				// Disables pagination
				.setIgnorePagination(true)

				// Experiment with this numbers to see the effect
				.setMargins(0, 0, 0, 0)

				// Set the title shown inside the Excel file
				.setTitle("Reporte de itinerarios")

				// Set the subtitle shown inside the Excel file
				.setSubtitle("Este reporte fue generado en " + new Date())

				// Set to true if you want to constrain your report within the
				// page boundaries
				// For longer reports, set it to false
				.setUseFullPageWidth(true);

		// Set the name of the file
		drb.setReportName("Reporte de itinerarios");

		// Build the report layout
		// Note this just the layout. It doesn't have any data yet!
		DynamicReport dr = drb.build();

		// Return the layout
		return dr;
	}
}