package com.tda.persistence.dao;

import com.tda.model.nurse.Vaxine;

public class VaxineDAO extends GenericDAOImpl<Vaxine> {
	@Override
	protected Class<Vaxine> getDomainClass() {
		return Vaxine.class;
	}
}
