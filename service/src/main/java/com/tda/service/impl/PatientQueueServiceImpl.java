package com.tda.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.patient.Patient;
import com.tda.model.patientqueue.PatientQueue;
import com.tda.persistence.dao.ApplicationUserDAO;
import com.tda.persistence.dao.PatientDAO;
import com.tda.persistence.dao.PatientQueueDAO;
import com.tda.service.api.PatientQueueService;

public class PatientQueueServiceImpl implements PatientQueueService {

	private PatientQueueDAO patientQueueDAO;
	private PatientDAO patientDAO;
	private ApplicationUserDAO applicationUserDAO;

	public void setPatientQueueDAO(PatientQueueDAO patientQueueDAO) {
		this.patientQueueDAO = patientQueueDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void setApplicationUserDAO(ApplicationUserDAO applicationUserDAO) {
		this.applicationUserDAO = applicationUserDAO;
	}

	@Transactional
	public void assignTo(Long patientId, Long applicationUserId) {
		// Search for the patient
		Patient pPatient = patientDAO.findById(patientId);
		// Search for the user
		ApplicationUser pUser = applicationUserDAO.findById(applicationUserId);

		if (pPatient != null && pUser != null) {
			// assing
			PatientQueue queue = new PatientQueue();
			queue.setPatient(pPatient);
			queue.setUser(pUser);
			queue.setTime(new Date());

			// persist
			patientQueueDAO.save(queue);
		}
	}

	@Transactional
	public void assignToS(Long patientId, String applicationUserUserName) {
		// Search for the patient
		Patient pPatient = patientDAO.findById(patientId);
		// Search for the user
		List<ApplicationUser> pUsers = applicationUserDAO
				.findByUsername(applicationUserUserName);
		// assing

		if (pPatient != null && pUsers.size() > 0) {
			PatientQueue queue = new PatientQueue();
			queue.setPatient(pPatient);
			queue.setUser(pUsers.get(0));
			queue.setTime(new Date());

			// persist
			patientQueueDAO.save(queue);
		}

	}

	@Transactional
	public List<Patient> getPatients(Long applicationUserId) {
		List<Patient> patients = new ArrayList<Patient>();

		List<PatientQueue> patientList = patientQueueDAO
				.findPatientsByApplicationUserId(applicationUserId);

		if (patientList != null)
			for (PatientQueue patientQueue : patientList) {
				patients.add(patientQueue.getPatient());
			}

		return patients;

	}

}
