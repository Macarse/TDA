package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;

public class PatientDAO extends GenericDAOImpl<Patient> {

	@Override
	protected Class<Patient> getDomainClass() {
		return Patient.class;
	}

	public List<Patient> findByFirstNameContaining(String name) {
		Patient exampleObject = PatientBuilder.createPatient()
				.withFirstName(name).build();
		return this.findByExample(exampleObject);
	}
}
