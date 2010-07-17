package com.tda.persistence;

import com.tda.model.Prueba;

public class PruebaDAO extends GenericDAOImpl<Prueba> implements GenericDAO<Prueba> {
	@Override
    protected Class<Prueba> getDomainClass() {
        return Prueba.class;
    }
}
