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
public class PatientReportLayout {

	/**
	 * Builds the report layout. This doesn't have any data yet. This is your
	 * template.
	 * 
	 * @return the layout
	 */
	public DynamicReport buildReportLayout() throws ColumnBuilderException,
			ClassNotFoundException {

		// The column fields must match the name of the properties in your
		// datasource
		// For example, if in you're datasource, you have a field name
		// firstName, then the column
		// field must have a fieldName as well

		// Create an instance of FastReportBuilder
		FastReportBuilder drb = new FastReportBuilder();

		// Format when adding columns:
		// Friendly column name, Field name (case-sensitive), Type, Width of the
		// column
		drb.addColumn("Id", "id", Long.class.getName(), 50)
				.addColumn("DNI", "dni", String.class.getName(), 50)
				.addColumn("Nombre", "firstName", String.class.getName(), 50)
				.addColumn("Apellido", "lastName", String.class.getName(), 50)
				// .addColumn("Sexo", "sex", Integer.class.getName(), 50)
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