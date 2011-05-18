package com.tda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

	private static String LIST = "report/list";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// model.addAttribute("configSync", new ConfigSync());
		return LIST;
	}
}
