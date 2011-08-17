package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;
import com.tda.model.utils.FormType;

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

	@SuppressWarnings("unchecked")
	public boolean isActiveForm(Long formId, FormType formType) {
		String whereQuery = "";

		switch (formType) {
		case dentist:
			whereQuery = "dentistform.id";
			break;
		case nurse:
			whereQuery = "nurseform.id";
			break;
		case pediatrician:
			whereQuery = "padiatricianform.id";
			break;
		case socialworker:
			whereQuery = "socialworkerform.id";
			break;
		}

		List<PatientInTrain> list = getHibernateTemplate().find(
				"from PatientInTrain as pit where pit." + whereQuery + "="
						+ formId);

		if (list == null || list.size() <= 0)
			return false;

		return true;
	}

	public PatientInTrain findByPatientId(Long patient) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("from PatientInTrain as pit where pit.patient.id=");
		stringBuilder.append(patient.toString());
		@SuppressWarnings("unchecked")
		List<PatientInTrain> list = getHibernateTemplate().find(
				stringBuilder.toString());

		if (list == null || list.size() != 1)
			return null;

		return list.get(0);
	}
}
