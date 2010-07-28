package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.patient.Patient;
import com.tda.model.patient.Sex;
import com.tda.presentation.client.service.PatientServiceGWTWrapper;
import com.tda.service.api.PatientService;

@SuppressWarnings("serial")
public class PatientServiceGWTWrapperImpl extends
		AutoinjectingRemoteServiceServlet implements PatientServiceGWTWrapper {

	private PatientService patientService;

	@Override
	protected void getBeansFromFactory(AutowireCapableBeanFactory beanFactory) {
		patientService = (PatientService) beanFactory.getBean("patientService");
	}

	public void save(Patient patient) {
		patientService.save(patient);

	}

	public void delete(Patient patient) {
		patientService.delete(patient);

	}

	public void update(Patient patient) {
		patientService.update(patient);

	}

	public Patient findById(Long id) {
		return patientService.findById(id);
	}

	public List<Patient> findAll() {
		return patientService.findAll();
	}

	public List<Patient> findByName(String name) {
		return patientService.findByName(name);
	}

	public List<Patient> findByExample(Patient example) {
		return patientService.findByExample(example);
	}

	public List<Patient> findByNameContaining(String name) {
		return patientService.findByNameContaining(name);
	}

	public List<Patient> findByDni(String dni) {
		return patientService.findByDni(dni);
	}

	public List<Patient> findBySex(Sex sex) {
		return patientService.findBySex(sex);
	}

	public List<Patient> findByMotherNameContaining(String motherName) {
		return patientService.findByMotherNameContaining(motherName);
	}

	public List<Patient> findByFatherNameContaining(String fatherName) {
		return patientService.findByFatherNameContaining(fatherName);
	}
}
