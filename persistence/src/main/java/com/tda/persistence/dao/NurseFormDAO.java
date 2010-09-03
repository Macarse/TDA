package com.tda.persistence.dao;

import com.tda.model.nurse.NurseForm;

public class NurseFormDAO extends GenericDAOImpl<NurseForm> {

	@Override
	protected Class<NurseForm> getDomainClass() {
		return NurseForm.class;
	}

}
