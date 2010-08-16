package com.tda.service.api;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.Sex;
import com.tda.persistence.paginator.Paginator;

public interface PatientService {

	void save(Patient patient);

	void delete(Patient patient);

	void update(Patient patient);

	Patient findById(Long id);

	List<Patient> findAll();

	List<Patient> findAllPaged(Paginator paginator);

	List<Patient> findByFirstName(String name);

	List<Patient> findByExample(Patient example);

	List<Patient> findByExamplePaged(Patient example, Paginator paginator);

	List<Patient> findByDni(String dni);

	List<Patient> findBySex(Sex sex);

}
