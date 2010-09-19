package com.tda.persistence.dao;

import com.tda.model.dentist.DentistForm;

public class DentistFormDAO extends GenericDAOImpl<DentistForm> {

	@Override
	protected Class<DentistForm> getDomainClass() {
		return DentistForm.class;
	}

}
