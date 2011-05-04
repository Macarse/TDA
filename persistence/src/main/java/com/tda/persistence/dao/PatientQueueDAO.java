package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.patientqueue.PatientQueue;

public class PatientQueueDAO extends GenericDAOImpl<PatientQueue> {

	@Override
	protected Class<PatientQueue> getDomainClass() {
		return PatientQueue.class;
	}

	public List<PatientQueue> findPatientsByApplicationUserId(
			Long applicationUserId) {
		@SuppressWarnings("unchecked")
		List<PatientQueue> list = getHibernateTemplate().find(
				"from PatientQueue pq where pq.user.id = " + applicationUserId);
		if (list == null || list.size() <= 0)
			return null;

		return list;
	}
}
