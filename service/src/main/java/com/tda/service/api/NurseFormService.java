package com.tda.service.api;

import java.util.Date;
import java.util.List;

import com.tda.model.nurse.NurseForm;
import com.tda.persistence.paginator.Paginator;

public interface NurseFormService {
	void save(NurseForm nurseForm);

	void delete(NurseForm nurseForm);

	void update(NurseForm nurseForm);

	NurseForm findById(Long id);

	List<NurseForm> findAll();

	List<NurseForm> findAllPaged(Paginator paginator);

	List<NurseForm> findByExample(NurseForm nurseForm);

	NurseForm findByPatientIdForDate(Long patientId, Date date);
}
