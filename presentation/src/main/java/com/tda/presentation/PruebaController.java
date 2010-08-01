package com.tda.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PruebaController {

	@RequestMapping(value = "/prueba")
	public ModelAndView helloWorld() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "Hello World!");
		return mav;
	}
}
