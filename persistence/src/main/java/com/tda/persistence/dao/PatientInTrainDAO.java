package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;

public class PatientInTrainDAO extends GenericDAOImpl<PatientInTrain> {

	@Override
	protected Class<PatientInTrain> getDomainClass() {
		return PatientInTrain.class;
	}

	public boolean isInTrain(Patient patient) {
		if (findByPatient(patient) != null)
			return true;

		return false;
	}

	public PatientInTrain findByPatient(Patient patient) {
		@SuppressWarnings("unchecked")
		List<PatientInTrain> list = getHibernateTemplate().find(
				"from PatientInTrain as pit where pit.patient.id="
						+ patient.getId());

		if (list == null || list.size() <= 0)
			return null;

		return list.get(0);
	}
}
