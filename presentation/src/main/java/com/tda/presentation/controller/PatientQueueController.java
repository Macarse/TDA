package com.tda.presentation.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.service.api.ApplicationUserService;
import com.tda.service.api.PatientInTrainService;
import com.tda.service.api.PatientQueueService;

@Controller
@RequestMapping(value = "/patientqueue")
@SessionAttributes({ "user" })
public class PatientQueueController {

	private PatientQueueService patientQueueService;
	private PatientInTrainService patientInTrainService;
	private ApplicationUserService applicationUserService;

	@ModelAttribute("user")
	public UserDetails getUser() {
		Object aux = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return ((UserDetails) aux);
	}

	@Autowired
	public void setPatientQueueService(PatientQueueService patientQueueService) {
		this.patientQueueService = patientQueueService;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	String get() {
		ApplicationUser user = (ApplicationUser) this.getUser();
		long applicationUserId = user.getId();
		Gson gson = new Gson();

		LinkedList<Patient> patients = new LinkedList<Patient>();

		for (Patient patient : patientQueueService
				.getPatients(applicationUserId)) {
			patients.add(patient);
		}

		return gson.toJson(patients);
	}

	@RequestMapping(value = "/assignto", method = RequestMethod.GET)
	public @ResponseBody
	void assignto(@RequestParam long patient, @RequestParam long medic) {
		PatientInTrain pit = patientInTrainService.findById(patient);
		ApplicationUser au = applicationUserService.findById(medic);

		if (pit != null && au != null) {
			pit.setUser(au);
			patientInTrainService.save(pit);
		}

		patientQueueService.assignTo(patient, medic);
	}

	@RequestMapping(value = "/assigntos", method = RequestMethod.GET)
	public @ResponseBody
	void assignto(@RequestParam long patient, @RequestParam String medic) {
		PatientInTrain pit = patientInTrainService.findById(patient);
		List<ApplicationUser> aus = applicationUserService
				.findByUsername(medic);

		ApplicationUser au = null;
		if (aus != null && aus.size() > 0)
			au = aus.get(0);

		if (pit != null && au != null) {
			pit.setUser(au);
			patientInTrainService.save(pit);
		}

		patientQueueService.assignToS(patient, medic);
	}

	@Autowired
	public void setPatientInTrainService(
			PatientInTrainService patientInTrainService) {
		this.patientInTrainService = patientInTrainService;
	}

	@Autowired
	public void setApplicationUserService(
			ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

}
