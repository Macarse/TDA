package com.tda.service.report;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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

import com.tda.model.patient.Patient;
import com.tda.model.report.AgeForDestinationReport;
import com.tda.model.report.AgeForReport;
import com.tda.model.report.InterconsultPerYearReport;
import com.tda.model.report.NbiForDestinationReport;
import com.tda.model.report.PrevalentDiagnosticForDestinationReport;
import com.tda.model.report.ScholarityByDestinationReport;
import com.tda.model.report.SexForReport;
import com.tda.model.utils.ConfigReport;
import com.tda.model.utils.ExportFormat;
import com.tda.persistence.dao.ItineraryDAO;
import com.tda.persistence.dao.PatientDAO;
import com.tda.persistence.dao.PatientcubeDAO;
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
	@SuppressWarnings("unused")
	private PlaceDAO placeDAO;
	private ItineraryDAO itineraryDAO;
	private PatientcubeDAO patientcubeDAO;

	public void setPatientcubeDAO(PatientcubeDAO patientcubeDAO) {
		this.patientcubeDAO = patientcubeDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void setPlaceDAO(PlaceDAO placeDAO) {
		this.placeDAO = placeDAO;
	}

	public void setItineraryDAO(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}

	public void downloadPatientReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws ColumnBuilderException,
			ClassNotFoundException, JRException {

		// Retrieve our data source
		JRDataSource ds = null;
		String fileName;
		String reportTitle;

		String DATE_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		if (configReport.getDateFrom() != null
				|| configReport.getDateTo() != null) {
			ds = new JRBeanCollectionDataSource(
					patientDAO.findPatientsAttendedByDate(
							configReport.getDateFrom(),
							configReport.getDateTo()));
			fileName = "PatientReportDate.";
			reportTitle = "Pacientes atendidos entre "
					+ sdf.format(configReport.getDateFrom()) + " y "
					+ sdf.format(configReport.getDateTo());
		} else if (configReport.getAgeFrom() != null
				|| configReport.getAgeTo() != null) {
			ds = new JRBeanCollectionDataSource(
					patientDAO.findPatientsBetweenAges(
							configReport.getAgeFrom(), configReport.getAgeTo()));
			fileName = "PatientReportAge.";
			reportTitle = "Pacientes entre " + configReport.getAgeFrom()
					+ " y " + configReport.getAgeTo() + " a�os";
		} else {
			ds = new JRBeanCollectionDataSource(patientDAO.findAll());
			fileName = "PatientReport.";
			reportTitle = "Pacientes registrados";
		}

		// Create our report layout
		// We delegate the reporting layout to a custom ReportLayout instance
		// The ReportLayout is a wrapper class I made. Feel free to remove or
		// modify it
		PatientReportLayout layout = new PatientReportLayout();
		DynamicReport dr = layout.buildReportLayout(reportTitle);

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
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

	public void downloadSexGraphReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws ColumnBuilderException,
			ClassNotFoundException, JRException, ChartBuilderException {

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

		Collection<Patient> allPatients = patientDAO.findAll();
		Collection<SexForReport> allPatientsSex = new ArrayList<SexForReport>();

		SexForReport male = new SexForReport();
		male.setSexId(0);
		male.setSexName("Masculino");
		male.setQuantity(0);
		SexForReport female = new SexForReport();
		female.setSexId(1);
		female.setSexName("Femenino");
		female.setQuantity(0);

		for (Patient pat : allPatients) {
			switch (pat.getSex()) {
			case female:
				female.setQuantity(1 + female.getQuantity());
				break;
			case male:
				male.setQuantity(1 + male.getQuantity());
				break;
			}
		}

		allPatientsSex.add(male);
		allPatientsSex.add(female);

		JRDataSource ds = new JRBeanCollectionDataSource(allPatientsSex);
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

		String fileName = "SexGraphReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	public void downloadItineraryReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws ColumnBuilderException,
			ClassNotFoundException, JRException, ChartBuilderException {

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

		String fileName = "ItineraryReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	public void downloadAgeGraphReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws ColumnBuilderException,
			ClassNotFoundException, JRException, ChartBuilderException {

		AgeGraphReportLayout layout = new AgeGraphReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		Collection<AgeForReport> groupedAge = patientDAO.findGroupedAge();
		JRDataSource ds = new JRBeanCollectionDataSource(groupedAge);
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

		String fileName = "AgeReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	public void downloadNbiForDestinationReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws ColumnBuilderException,
			ChartBuilderException, ClassNotFoundException, JRException {

		NbiForDestinationReportLayout layout = new NbiForDestinationReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		Collection<NbiForDestinationReport> groupedNbi = patientcubeDAO
				.findNbiForDestination();

		JRDataSource ds = new JRBeanCollectionDataSource(groupedNbi);
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

		String fileName = "Nbifordestinationreport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	public void downloadInterconsultPerYearReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws ColumnBuilderException,
			ChartBuilderException, ClassNotFoundException, JRException {

		InterconsultPerYearReportLayout layout = new InterconsultPerYearReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		Collection<InterconsultPerYearReport> grouped = patientcubeDAO
				.findInterconsultPerYear();

		JRDataSource ds = new JRBeanCollectionDataSource(grouped);
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

		String fileName = "InterconsultantPerYearReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);

	}

	public void downloadAgeForDestinationReport(HttpServletRequest request,
			HttpServletResponse response, ExportFormat format,
			ConfigReport configReport) throws JRException,
			ColumnBuilderException, ChartBuilderException,
			ClassNotFoundException {

		AgeForDestinationReportLayout layout = new AgeForDestinationReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		Collection<AgeForDestinationReport> grouped = patientcubeDAO
				.findAgeForDestination();

		JRDataSource ds = new JRBeanCollectionDataSource(grouped);
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

		String fileName = "AgeForDestinationReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

	public void downloadPrevalentDiagnosticForDestinationReport(
			HttpServletRequest request, HttpServletResponse response,
			ExportFormat format, ConfigReport configReport) throws JRException,
			ColumnBuilderException, ChartBuilderException,
			ClassNotFoundException {

		PrevalentDiagnosticForDestinationReportLayout layout = new PrevalentDiagnosticForDestinationReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		Collection<PrevalentDiagnosticForDestinationReport> groupedNbi = patientcubeDAO
				.findPrevalentDiagnosticForDestination();

		JRDataSource ds = new JRBeanCollectionDataSource(groupedNbi);
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

		String fileName = "PrevalentDiagnosticForDestinationReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);

	}

	public void downloadScholarityByDestinationReport(
			HttpServletRequest request, HttpServletResponse response,
			ExportFormat format, ConfigReport configReport)
			throws ColumnBuilderException, ChartBuilderException,
			ClassNotFoundException, JRException {
		ScholarityByDestinationLayout layout = new ScholarityByDestinationLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		@SuppressWarnings("rawtypes")
		HashMap params = new HashMap();

		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		Collection<ScholarityByDestinationReport> groupedNbi = patientcubeDAO
				.findScholarityByDestination();

		JRDataSource ds = new JRBeanCollectionDataSource(groupedNbi);
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

		String fileName = "ScholarityByDestinationReport.";

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
		case HTML:
			exporter.exportHTML(request, jp, baos);
			fileName += "html";
			response.setContentType("text/html");
			break;
		}

		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);

		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}

}