package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.itinerary.PatientCube;
import com.tda.model.report.NbiForDestinationReport;
import com.tda.persistence.dao.PatientcubeDAO;
import com.tda.service.api.PatientcubeService;

public class PatientcubeServiceImpl implements PatientcubeService {

	private PatientcubeDAO patientcubeDAO;

	public void setPatientcubeDAO(PatientcubeDAO patientcubeDAO) {
		this.patientcubeDAO = patientcubeDAO;
	}

	@Transactional
	public void save(PatientCube patient) {
		patientcubeDAO.save(patient);
	}

	@Transactional
	public void delete(PatientCube patient) {
		patientcubeDAO.delete(patient);
	}

	@Transactional
	public void update(PatientCube patient) {
		patientcubeDAO.update(patient);
	}

	@Transactional(readOnly = true)
	public PatientCube findById(Long id) {
		return patientcubeDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<PatientCube> findAll() {
		return patientcubeDAO.findAll();
	}

	public List<PatientCube> findByExample(PatientCube example) {
		return patientcubeDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<NbiForDestinationReport> findNbiForDestination() {
		return patientcubeDAO.findNbiForDestination();
	}
}
