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
import com.tda.service.report.ReportService;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

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
	public void doPatientReport(
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
			reportService.downloadPatientReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/patientReportDate", method = RequestMethod.GET)
	public void doPatientReportDate(
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
			reportService.downloadPatientReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/sexGraphReport", method = RequestMethod.GET)
	public void doSexGraphReport(
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
			reportService.downloadSexGraphReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/itineraryReport", method = RequestMethod.GET)
	public void doItineraryReport(
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
			reportService.downloadItineraryReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/patientReportAge", method = RequestMethod.GET)
	public void doPatientReportAge(
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
			reportService.downloadPatientReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/ageGraphReport", method = RequestMethod.GET)
	public void doAgeGraphReport(
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
			reportService.downloadAgeGraphReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/nbiForDestinationReport", method = RequestMethod.GET)
	public void doNbiForDestinationReport(
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
			reportService.downloadNbiForDestinationReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/interconsultPerYearReport", method = RequestMethod.GET)
	public void doInterconsultPerYearReport(
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
			reportService.downloadInterconsultPerYearReport(request, response,
					configReport.getFormat(), configReport);
		}
	}

	@RequestMapping(value = "/ageForDestinationReport", method = RequestMethod.GET)
	public void doAgeForDestinationReport(
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
			reportService.downloadAgeForDestinationReport(request, response,
					configReport.getFormat(), configReport);
		}
	}
}
