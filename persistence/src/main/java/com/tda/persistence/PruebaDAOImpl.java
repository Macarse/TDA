package com.tda.persistence;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tda.model.Prueba;

public class PruebaDAOImpl extends HibernateDaoSupport implements PruebaDAO {

	public void save(Prueba prueba) {
		getHibernateTemplate().save(prueba);
	}

	public void delete(Prueba prueba) {
		getHibernateTemplate().delete(prueba);
	}

	public Prueba getById(int id) {
		return (Prueba) getHibernateTemplate().get(Prueba.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Prueba> getAll() {
		return getHibernateTemplate().find("from " + Prueba.class.getName());
	}

}
