package com.tda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.model.patient.Sex;
import com.tda.model.utils.FormType;
import com.tda.persistence.dao.PatientInTrainDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.PatientInTrainService;
import com.tda.service.api.PatientService;

public class PatientInTrainServiceImpl implements PatientInTrainService {

	private PatientInTrainDAO patientInTrainDAO;
	private PatientService patientService;

	public void setPatientInTrainDAO(PatientInTrainDAO patientInTrainDAO) {
		this.patientInTrainDAO = patientInTrainDAO;
	}

	@Transactional
	public void save(PatientInTrain patient) {
		patientInTrainDAO.save(patient);
	}

	@Transactional
	public void delete(PatientInTrain patient) {
		patientInTrainDAO.delete(patient);
	}

	@Transactional
	public void update(PatientInTrain patient) {
		patientInTrainDAO.update(patient);
	}

	@Transactional(readOnly = true)
	public PatientInTrain findById(Long id) {
		return patientInTrainDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<PatientInTrain> findAll() {
		return patientInTrainDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<PatientInTrain> findByFirstName(String name) {
		PatientInTrain example = new PatientInTrain();
		return patientInTrainDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<PatientInTrain> findByExample(PatientInTrain example) {
		return patientInTrainDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<PatientInTrain> findByDni(String dni) {
		List<Patient> results = patientService.findByDni(dni);
		if (results != null && results.size() == 1) {
			PatientInTrain inTrain = patientInTrainDAO.findByPatient(results
					.get(0));
			if (inTrain != null) {
				List<PatientInTrain> list = new ArrayList<PatientInTrain>();
				list.add(inTrain);
				return list;
			}
		}

		return null;
	}

	@Transactional(readOnly = true)
	public List<PatientInTrain> findBySex(Sex sex) {
		PatientInTrain example = new PatientInTrain();
		Patient patient = new Patient();
		patient.setSex(sex);
		example.setPatient(patient);
		return patientInTrainDAO.findByExample(example);
	}

	public List<PatientInTrain> findAllPaged(Paginator paginator) {
		return patientInTrainDAO.findAllPaged(paginator);
	}

	public List<PatientInTrain> findByExamplePaged(PatientInTrain example,
			Paginator paginator) {
		return patientInTrainDAO.findByExamplePaged(example, paginator);
	}

	public boolean isInTrain(Patient patient) {
		return patientInTrainDAO.isInTrain(patient);
	}

	public PatientInTrain findByPatient(Patient patient) {
		return patientInTrainDAO.findByPatient(patient);
	}

	public boolean isActiveForm(Long formId, FormType formType) {
		return patientInTrainDAO.isActiveForm(formId, formType);
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public PatientInTrain findByPatientId(long patient) {
		return patientInTrainDAO.findByPatientId(patient);
	}
}
