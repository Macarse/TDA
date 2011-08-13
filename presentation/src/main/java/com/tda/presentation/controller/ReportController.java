package com.tda.presentation.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.fdvs.dj.domain.builders.ChartBuilderException;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;

import com.tda.model.utils.ConfigReport;
import com.tda.model.utils.ExportFormat;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.report.ReportService;

@Controller
@RequestMapping(value = "/report")
public class ReportController extends CommonController {

	private ReportService reportService;
	private static String LIST = "report/list";

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("configReport", new ConfigReport());
		model.addAttribute("allFormat", ExportFormat.values());
		return LIST;
	}

	@RequestMapping(value = "/patientReport", method = RequestMethod.GET)
	public ModelAndView doPatientReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadPatientReport(request, response,
						configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/patientReportDate", method = RequestMethod.GET)
	public ModelAndView doPatientReportDate(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadPatientReport(request, response,
						configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/sexGraphReport", method = RequestMethod.GET)
	public ModelAndView doSexGraphReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadSexGraphReport(request, response,
						configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/itineraryReport", method = RequestMethod.GET)
	public ModelAndView doItineraryReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadItineraryReport(request, response,
						configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/patientReportAge", method = RequestMethod.GET)
	public ModelAndView doPatientReportAge(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadPatientReport(request, response,
						configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/ageGraphReport", method = RequestMethod.GET)
	public ModelAndView doAgeGraphReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadAgeGraphReport(request, response,
						configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/nbiForDestinationReport", method = RequestMethod.GET)
	public ModelAndView doNbiForDestinationReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadNbiForDestinationReport(request,
						response, configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/interconsultPerYearReport", method = RequestMethod.GET)
	public ModelAndView doInterconsultPerYearReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadInterconsultPerYearReport(request,
						response, configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/scholarityByDestinationReport", method = RequestMethod.GET)
	public ModelAndView doScholarityByDestinationReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadScholarityByDestinationReport(request,
						response, configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/ageForDestinationReport", method = RequestMethod.GET)
	public ModelAndView doAgeForDestinationReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadAgeForDestinationReport(request,
						response, configReport.getFormat(), configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}

	@RequestMapping(value = "/prevalentDiagnosticForDestinationReport", method = RequestMethod.GET)
	public ModelAndView doPrevalentDiagnosticForDestinationReport(
			@Valid @ModelAttribute ConfigReport configReport,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ColumnBuilderException,
			JRException, ChartBuilderException {

		ModelAndView model = new ModelAndView(LIST);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configReport", configReport);
		} else {
			try {
				reportService.downloadPrevalentDiagnosticForDestinationReport(
						request, response, configReport.getFormat(),
						configReport);
			} catch (NoDataFoundException e) {
				model.addObject("configReport", configReport);
				model.addObject("allFormat", ExportFormat.values());
				model.addObject("nodata", true);
				return model;
			}
		}
		return null;
	}
}
