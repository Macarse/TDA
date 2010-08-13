package com.tda.service.impl;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;
import com.tda.model.patient.Sex;
import com.tda.persistence.dao.PatientDAO;
import com.tda.service.api.PatientService;

public class PatientServiceImpl implements PatientService {

	private PatientDAO patientDAO;

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void save(Patient patient) {
		patientDAO.save(patient);
	}

	public void delete(Patient patient) {
		patientDAO.delete(patient);
	}

	public void update(Patient patient) {
		patientDAO.update(patient);
	}

	public Patient findById(Long id) {
		return patientDAO.findById(id);
	}

	public List<Patient> findAll() {
		return patientDAO.findAll();
	}

	public List<Patient> findByFirstName(String name) {
		Patient example = PatientBuilder.createPatient().withFirstName(name)
				.build();
		return patientDAO.findByExample(example);
	}

	public List<Patient> findByExample(Patient example) {
		return patientDAO.findByExample(example);
	}

	public List<Patient> findByNameContaining(String name) {
		return patientDAO.findByNameContaining(name);
	}

	public List<Patient> findByDni(String dni) {
		Patient example = PatientBuilder.createPatient().withDni(dni).build();
		return patientDAO.findByExample(example);
	}

	public List<Patient> findBySex(Sex sex) {
		Patient example = PatientBuilder.createPatient().withSex(sex).build();
		return patientDAO.findByExample(example);
	}

	public List<Patient> findByMotherNameContaining(String motherName) {
		return patientDAO.findByMotherNameContaining(motherName);
	}

	public List<Patient> findByFatherNameContaining(String fatherName) {
		return patientDAO.findByFatherNameContaining(fatherName);
	}

}
