package com.tda.persistence.dao;

import java.util.Date;
import java.util.List;

import com.tda.model.nurse.NurseForm;
import com.tda.model.patient.Patient;
import com.tda.model.utils.DateUtils;

public class NurseFormDAO extends GenericDAOImpl<NurseForm> {
	private PatientDAO patientDAO;

	@Override
	protected Class<NurseForm> getDomainClass() {
		return NurseForm.class;
	}

	public NurseForm findByPatientIdForDate(Long patientId, Date date) {
		Patient patient = patientDAO.findById(patientId);

		if (patient == null)
			return null;

		NurseForm exampleObject = new NurseForm();
		exampleObject.setPatient(patient);

		org.hibernate.Query query = getSession().createQuery(
				"from NurseForm n where n.patient.id=" + patientId
						+ " order by n.fillingDate DESC");

		@SuppressWarnings("unchecked")
		List<NurseForm> formList = query.list();

		if (formList.size() == 0
				|| !DateUtils.isToday(formList.get(0).getFillingDate()))
			return null;

		return formList.get(0);
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}
}
