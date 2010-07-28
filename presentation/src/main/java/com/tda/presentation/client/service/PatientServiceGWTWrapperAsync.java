package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.patient.Patient;
import com.tda.model.patient.Sex;

public interface PatientServiceGWTWrapperAsync {

	void delete(Patient patient, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<Patient>> callback);

	void findByDni(String dni, AsyncCallback<List<Patient>> callback);

	void findByExample(Patient example, AsyncCallback<List<Patient>> callback);

	void findByFatherNameContaining(String fatherName,
			AsyncCallback<List<Patient>> callback);

	void findById(Long id, AsyncCallback<Patient> callback);

	void findByMotherNameContaining(String motherName,
			AsyncCallback<List<Patient>> callback);

	void findByName(String name, AsyncCallback<List<Patient>> callback);

	void findByNameContaining(String name, AsyncCallback<List<Patient>> callback);

	void findBySex(Sex sex, AsyncCallback<List<Patient>> callback);

	void save(Patient patient, AsyncCallback<Void> callback);

	void update(Patient patient, AsyncCallback<Void> callback);

}
