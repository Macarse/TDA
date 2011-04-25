package com.tda.persistence.dao;

import com.tda.model.patientqueue.PatientQueue;

public class PatientQueueDAO extends GenericDAOImpl<PatientQueue> {

	@Override
	protected Class<PatientQueue> getDomainClass() {
		return PatientQueue.class;
	}
	
//	public List<Patient> findPatientsByApplicationUserId(Long applicationUserId){
//		
//	}
	

}
