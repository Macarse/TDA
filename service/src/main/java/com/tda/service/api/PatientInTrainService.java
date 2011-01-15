package com.tda.service.api;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.model.patient.Sex;
import com.tda.persistence.paginator.Paginator;

public interface PatientInTrainService {

	void save(PatientInTrain patient);

	void delete(PatientInTrain patient);

	void update(PatientInTrain patient);

	PatientInTrain findById(Long id);

	List<PatientInTrain> findAll();

	List<PatientInTrain> findAllPaged(Paginator paginator);

	List<PatientInTrain> findByFirstName(String name);

	List<PatientInTrain> findByExample(PatientInTrain example);

	List<PatientInTrain> findByExamplePaged(PatientInTrain example,
			Paginator paginator);

	List<PatientInTrain> findByDni(String dni);

	List<PatientInTrain> findBySex(Sex sex);

	boolean isInTrain(Patient patient);

	PatientInTrain findByPatient(Patient patient);
}
