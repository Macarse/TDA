package com.tda.presentation.controller;

import java.io.File;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
public class SyncController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String MAIN = "sync/main";

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("configSync", new ConfigSync());
		return MAIN;
	}

	@RequestMapping(value = "/do", method = RequestMethod.GET)
	public ModelAndView sync(@Valid @ModelAttribute ConfigSync configSync,
			BindingResult result, HttpServletRequest request) {
		// Variables a parametrizas

		String maatkitDir = request.getSession().getServletContext()
				.getRealPath("WEB-INF/maatkit-7332");

		// String maatkitDir =
		// "/Users/iandrono/Documents/ITBA/PF/TDA/presentation/src/main/webapp/maatkit-7332";
		// String maatkitDir = System.getProperty("user.dir") + "/maatkit-7332";
		String localPort = "8889";
		String bothDBUser = "root";
		String bothDBpass = "root";
		String respMessage = "";

		ModelAndView model = new ModelAndView(MAIN);

		if (result.hasErrors()) {
			// Primero valido el formulario
			model.addObject("configSync", configSync);
		} else {
			// 1 check que esta la lib de sync:
			File file = new File(maatkitDir + "/bin/mk-table-sync");
			if (!file.exists())
				respMessage = "Error: libreria de sincronizacion no encontrada";
			else {
				// 2 check si tenemos perl:
				Runtime runtime = Runtime.getRuntime();
				boolean hasPerl = true;
				Process p;
				try {
					p = runtime.exec("perl --version");
					p.waitFor();
				} catch (Exception e1) {
					hasPerl = false;
					respMessage = "Error: perl no esta instalado";
				}

				if (hasPerl) {
					try {
						Process process = runtime.exec("perl " + maatkitDir
								+ "/bin/mk-table-sync --execute h=localhost:"
								+ localPort + ",u=" + bothDBUser + ",p="
								+ bothDBpass + " --databases tda h="
								+ configSync.getIp() + ":"
								+ configSync.getPort() + " --verbose --wait 0");

						Worker worker = new Worker(process);
						worker.start();
						try {
							worker.join(10000);
							if (worker.exit != null)
								/*
								 * STATUS MEANING ======
								 * ================================
								 * ======================= 0 Success. 1 Internal
								 * error. 2 At least one table differed on the
								 * destination. 3 Combination of 1 and 2.
								 */
								switch (worker.exit) {
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
								default:
									respMessage = "Error desconocido";
								}
							else
								respMessage = "Timeout: intente mas tarde";
						} catch (InterruptedException ex) {
							worker.interrupt();
						} finally {
							process.destroy();
						}
					} catch (Exception e) {
						System.out.println("Exception: " + e.toString());
					}
				}
			}
			model.addObject("resultMessage", respMessage);
		}
		return model;
	}
}

class Worker extends Thread {
	private final Process process;
	Integer exit;

	Worker(Process process) {
		this.process = process;
	}

	public void run() {
		try {
			exit = process.waitFor();
		} catch (InterruptedException ignore) {
			return;
		}
	}
}
