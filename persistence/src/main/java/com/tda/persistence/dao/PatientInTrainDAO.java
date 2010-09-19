package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientInTrain;

public class PatientInTrainDAO extends GenericDAOImpl<PatientInTrain> {

	@Override
	protected Class<PatientInTrain> getDomainClass() {
		return PatientInTrain.class;
	}
	
	public boolean isInTrain(Patient patient)
	{
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from PatientInTrain as pit,Patient as p where p.id=pit.patient.id and p.id="+patient.getId());
		if( list.size() != 0)
			return true;
		
		return false;
	}
}
