package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.patient.PatientInTrain;
import com.tda.model.patientqueue.PatientQueue;

public class PatientQueueDAO extends GenericDAOImpl<PatientQueue> {

	@Override
	protected Class<PatientQueue> getDomainClass() {
		return PatientQueue.class;
	}
	
	public List<PatientQueue> findPatientsByApplicationUserId(
			Long applicationUserId) {
		@SuppressWarnings("unchecked")
		//List<PatientQueue> list = getHibernateTemplate().find(
		//		"from PatientQueue pq where pq.user.id = " + applicationUserId);
		List<PatientQueue> list = getHibernateTemplate().find(
				//"from PatientQueue pq inner join com.tda.model.PatientInTrain pit on pit.patient.id = pq.patient.id WHERE pq.user.id = " + applicationUserId + " and pq.id in (select max(id) from PatientQueue group by patient_id)");
				"from PatientQueue pq WHERE pq.user.id = " + applicationUserId + " and pq.id in (select max(id) from PatientQueue group by patient_id) and pq.id in (select patient.id from PatientInTrain)");
		
		if (list == null || list.size() <= 0)
			return null;

		return list;
	}
}
