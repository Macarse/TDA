package com.tda.presentation.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;

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
		// model.addAttribute("configSync", new ConfigSync());
		return LIST;
	}

	@RequestMapping(value = "/patientReport", method = RequestMethod.GET)
	public void doSalesReportXLS(HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException,
			SQLException, ColumnBuilderException, JRException {

		// Call DownloadService to do the actual report processing
		reportService.downloadXLS(response);
	}
}
