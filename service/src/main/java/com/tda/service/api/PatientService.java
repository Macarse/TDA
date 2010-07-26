package com.tda.service.api;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.Sex;

public interface PatientService {

	void save(Patient patient);

	void delete(Patient patient);

	void update(Patient patient);

	Patient findById(Long id);

	List<Patient> findAll();

	List<Patient> findByName(String name);

	List<Patient> findByExample(Patient example);

	List<Patient> findByNameContaining(String name);

	List<Patient> findByDni(String dni);

	List<Patient> findBySex(Sex sex);

	List<Patient> findByMotherNameContaining(String motherName);

	List<Patient> findByFatherNameContaining(String fatherName);

}
