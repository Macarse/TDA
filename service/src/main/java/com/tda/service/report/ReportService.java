package com.tda.service.report;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.transaction.annotation.Transactional;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ChartBuilderException;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;

import com.tda.model.utils.ConfigReport;
import com.tda.model.utils.ExportFormat;
import com.tda.persistence.dao.ItineraryDAO;
import com.tda.persistence.dao.PatientDAO;
import com.tda.persistence.dao.PlaceDAO;

/**
 * Service for processing DynamicJasper reports Issues
 * http://jasperforge.org/plugins
 * /espforum/view.php?group_id=102&forumid=103&topicid=80305
 * http://dynamicjasper.com/forum/viewtopic.php?f=8&t=7248
 */
@Transactional
public class ReportService {

	private PatientDAO patientDAO;
	private PlaceDAO placeDAO;
	private ItineraryDAO itineraryDAO;

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void setPlaceDAO(PlaceDAO placeDAO) {
		this.placeDAO = placeDAO;
	}

	public void setItineraryDAO(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}

	public void downloadPatientReport(HttpServletResponse response,
			ExportFormat format, ConfigReport configReport)
			throws ColumnBuilderException, ClassNotFoundException, JRException {

		// Retrieve our data source
		JRDataSource ds = null;

		if (configReport.getDateFrom() != null
				|| configReport.getDateTo() != null)
			ds = new JRBeanCollectionDataSource(
					patientDAO.findPatientsAttendedByDate(
							configReport.getDateFrom(),
							configReport.getDateTo()));
		else
			ds = new JRBeanCollectionDataSource(patientDAO.findAll());

		// Create our report layout
		// We delegate the reporting layout to a custom ReportLayout instance
		// The ReportLayout is a wrapper class I made. Feel free to remove or
		// modify it
		PatientReportLayout layout = new PatientReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		// Creates the JasperPrint object
		// It needs a JasperReport layout and a datasource
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		// Create our output byte stream
		// This is the stream where the data will be written
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Export to output stream
		// The data will be exported to the ByteArrayOutputStream baos
		// We delegate the exporting to a custom Exporter instance
		// The Exporter is a wrapper class I made. Feel free to remove or modify
		// it
		Exporter exporter = new Exporter();

		String fileName = "PatientReport.";

		switch (format) {
		case XLS:
			exporter.exportXLS(jp, baos);
			fileName += "xls";
			response.setContentType("application/vnd.ms-excel");
			break;
		case PDF:
			exporter.exportPDF(jp, baos);
			fileName += "pdf";
			response.setContentType("application/pdf");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	private void writeReportToResponseStream(HttpServletResponse response,
			ByteArrayOutputStream baos) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
		}
	}

	public void downloadSexGraphReport(HttpServletResponse response,
			ExportFormat format, ConfigReport configReport)
			throws ColumnBuilderException, ClassNotFoundException, JRException,
			ChartBuilderException {

		SexGraphReportLayout layout = new SexGraphReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		// Creates the JasperPrint object
		// It needs a JasperReport layout and a datasource
		JRDataSource ds = new JRBeanCollectionDataSource(patientDAO.findAll());
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		// Create our output byte stream
		// This is the stream where the data will be written
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Export to output stream
		// The data will be exported to the ByteArrayOutputStream baos
		// We delegate the exporting to a custom Exporter instance
		// The Exporter is a wrapper class I made. Feel free to remove or modify
		// it
		Exporter exporter = new Exporter();

		String fileName = "PatientReport.";

		switch (format) {
		case XLS:
			exporter.exportXLS(jp, baos);
			fileName += "xls";
			response.setContentType("application/vnd.ms-excel");
			break;
		case PDF:
			exporter.exportPDF(jp, baos);
			fileName += "pdf";
			response.setContentType("application/pdf");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	public void downloadItineraryReport(HttpServletResponse response,
			ExportFormat format, ConfigReport configReport)
			throws ColumnBuilderException, ClassNotFoundException, JRException,
			ChartBuilderException {

		ItineraryReportLayout layout = new ItineraryReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		// Creates the JasperPrint object
		// It needs a JasperReport layout and a datasource
		JRDataSource ds = new JRBeanCollectionDataSource(
				itineraryDAO.reportCollection());
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		// Create our output byte stream
		// This is the stream where the data will be written
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Export to output stream
		// The data will be exported to the ByteArrayOutputStream baos
		// We delegate the exporting to a custom Exporter instance
		// The Exporter is a wrapper class I made. Feel free to remove or modify
		// it
		Exporter exporter = new Exporter();

		String fileName = "PlacesReport.";

		switch (format) {
		case XLS:
			exporter.exportXLS(jp, baos);
			fileName += "xls";
			response.setContentType("application/vnd.ms-excel");
			break;
		case PDF:
			exporter.exportPDF(jp, baos);
			fileName += "pdf";
			response.setContentType("application/pdf");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

}