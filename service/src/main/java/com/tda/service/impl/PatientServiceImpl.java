package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;
import com.tda.model.patient.Sex;
import com.tda.persistence.dao.PatientDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.PatientService;

public class PatientServiceImpl implements PatientService {

	private PatientDAO patientDAO;

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	@Transactional
	public void save(Patient patient) {
		patientDAO.save(patient);
	}

	@Transactional
	public void delete(Patient patient) {
		patientDAO.delete(patient);
	}

	@Transactional
	public void update(Patient patient) {
		patientDAO.update(patient);
	}

	@Transactional(readOnly = true)
	public Patient findById(Long id) {
		return patientDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Patient> findAll() {
		return patientDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<Patient> findByFirstName(String name) {
		Patient example = PatientBuilder.createPatient().withFirstName(name)
				.build();
		return patientDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Patient> findByExample(Patient example) {
		return patientDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Patient> findByFirstNameContaining(String name) {
		return patientDAO.findByFirstNameContaining(name);
	}

	@Transactional(readOnly = true)
	public List<Patient> findByDni(String dni) {
		Patient example = new Patient();
		example.setDni(dni);
		return patientDAO.findByExample(example, true);
	}

	@Transactional(readOnly = true)
	public List<Patient> findBySex(Sex sex) {
		Patient example = new Patient();
		example.setSex(sex);
		return patientDAO.findByExample(example);
	}

	public List<Patient> findAllPaged(Paginator paginator) {
		return patientDAO.findAllPaged(paginator);
	}

	public List<Patient> findByExamplePaged(Patient example, Paginator paginator) {
		return patientDAO.findByExamplePaged(example, paginator);
	}

}
