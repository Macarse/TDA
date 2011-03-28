package com.tda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/sync")
public class SyncController {
	@RequestMapping(value = "/do", method = RequestMethod.GET)
	public String sync() {
		// Sync all tables on host1 to host2 and host3:
		// perl mk-table-sync --execute host1 host2 host3

		Process process;

		try {
			process = Runtime
					.getRuntime()
					.exec("perl /Users/iandrono/Documents/ITBA/PF/maatkit-7332/bin/mk-table-sync --execute h=localhost:8889,u=root,p=root --databases tda h=172.16.248.199 --verbose");
			process.waitFor();
			/*
			 * STATUS MEANING ======
			 * ======================================================= 0
			 * Success. 1 Internal error. 2 At least one table differed on the
			 * destination. 3 Combination of 1 and 2.
			 */
			if (process.exitValue() == 0 || process.exitValue() == 2) {
				System.out.println("Command Successful");
			} else {
				System.out.println("Command Failure");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}

		return "redirect:/j_spring_security_logout";
	}
}
