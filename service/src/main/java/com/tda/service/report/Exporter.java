package com.tda.service.report;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

/**
 * Everything under the package org.krams.tutorial.jasper are settings imposed
 * by Jasper (not DynamicJasper)
 * <p>
 * 
 * An exporter for exporting the report in various formats, i.e Excel, PDF, CSV.
 * Here we declare a PDF exporter
 */
public class Exporter {

	/**
	 * Exports a report to XLS (Excel) format. You can declare another export
	 * here, i.e PDF or CSV. You don't really need to create a separate class or
	 * method for the exporter. You can call it directly within your Service or
	 * Controller.
	 * 
	 * @param jp
	 *            the JasperPrint object
	 * @param baos
	 *            the ByteArrayOutputStream where the report will be written
	 */
	public void exportXLS(JasperPrint jp, ByteArrayOutputStream baos)
			throws JRException {
		// Create a JRXlsExporter instance
		JRXlsExporter exporter = new JRXlsExporter();

		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		// Excel specific parameters
		// Check the Jasper (not DynamicJasper) docs for a description of these
		// settings. Most are
		// self-documenting
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);

		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}

	public void exportPDF(JasperPrint jp, ByteArrayOutputStream baos)
			throws JRException {
		// Create a JRXlsExporter instance
		JRPdfExporter exporter = new JRPdfExporter();

		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}

	public void exportHTML(HttpServletRequest request, JasperPrint jp,
			ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		JRHtmlExporter exporter = new JRHtmlExporter();

		request.getSession().setAttribute(
				ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

		Random rnd = new Random();
		String imgExtension = String.valueOf(rnd.nextInt());
		exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				request.getContextPath() + "/reportImage?rnd=" + imgExtension
						+ "&image=");
		// exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
		// Boolean.FALSE);

		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}
}