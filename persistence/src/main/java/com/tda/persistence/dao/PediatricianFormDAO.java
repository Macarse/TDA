package com.tda.persistence.dao;

import com.tda.model.pediatrician.PediatricianForm;

public class PediatricianFormDAO extends GenericDAOImpl<PediatricianForm> {

	@Override
	protected Class<PediatricianForm> getDomainClass() {
		return PediatricianForm.class;
	}

}
