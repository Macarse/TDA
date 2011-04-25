package com.tda.service.api;

import java.util.List;

import com.tda.model.patient.Patient;

public interface PatientQueueService {
	void assignTo(Long patientId, Long applicationUserId);
	
	List<Patient> getPatients(Long applicationUserId);
}
