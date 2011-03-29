package com.tda.presentation.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.utils.ConfigSync;

@Controller
@RequestMapping(value = "/sync")
public class SyncController {

	private static String MAIN = "sync/main";

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("configSync", new ConfigSync());
		return MAIN;
	}

	@RequestMapping(value = "/do", method = RequestMethod.GET)
	public ModelAndView sync(@Valid @ModelAttribute ConfigSync configSync,
			BindingResult result) {
		Process process;
		String respMessage = "";
		// Variables a parametrizas
		String maatkitDir = "/Users/iandrono/Documents/ITBA/PF/maatkit-7332";
		String localPort = "8889";
		String bothDBUser = "root";
		String bothDBpass = "root";

		ModelAndView model = new ModelAndView(MAIN);

		if (result.hasErrors()) {
			model.addObject("configSync", configSync);
		} else {

			try {
				process = Runtime.getRuntime().exec(
						"perl " + maatkitDir
								+ "/bin/mk-table-sync --execute h=localhost:"
								+ localPort + ",u=" + bothDBUser + ",p="
								+ bothDBpass + " --databases tda h="
								+ configSync.getIp() + ":"
								+ configSync.getPort() + " --verbose");
				process.waitFor();
				/*
				 * STATUS MEANING ======
				 * ======================================================= 0
				 * Success. 1 Internal error. 2 At least one table differed on
				 * the destination. 3 Combination of 1 and 2.
				 */
				switch (process.exitValue()) {
				case 0:
					respMessage = "No hubia cambios pendientes";
					break;
				case 1:
					respMessage = "Sincronizacion abortada por error interno";
				case 2:
					respMessage = "Cambios sincronizados!";
					break;
				case 3:
					respMessage = "Hubo cambios pero termino con error";
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e.toString());
			}
			model.addObject("resultMessage", respMessage);
		}
		return model;
	}
}
