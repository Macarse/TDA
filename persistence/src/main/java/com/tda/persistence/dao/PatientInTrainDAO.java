package com.tda.persistence.dao;

import com.tda.model.patient.PatientInTrain;

public class PatientInTrainDAO extends GenericDAOImpl<PatientInTrain> {

	@Override
	protected Class<PatientInTrain> getDomainClass() {
		return PatientInTrain.class;
	}
}
