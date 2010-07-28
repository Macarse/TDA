package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.model.patient.Patient;
import com.tda.model.patient.Sex;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("patient")
public interface PatientServiceGWTWrapper extends RemoteService {

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
