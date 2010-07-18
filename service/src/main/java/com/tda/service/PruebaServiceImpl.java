package com.tda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.Prueba;
import com.tda.persistence.PruebaDAO;

public class PruebaServiceImpl implements PruebaService {

	PruebaDAO pruebaDAO;

	@Autowired
	@Required
	public void setPruebaDAO(PruebaDAO pruebaDAO) {
		this.pruebaDAO = pruebaDAO;
	}

	@Transactional(readOnly = false)
	public void save(Prueba prueba) {
		pruebaDAO.save(prueba);
	}

	@Transactional(readOnly = false)
	public void delete(Prueba prueba) {
		pruebaDAO.delete(prueba);
	}

	@Transactional(readOnly = true)
	public Prueba getById(int id) {
		return pruebaDAO.getById(id);
	}

	@Transactional(readOnly = true)
	public List<Prueba> getAll() {
		return pruebaDAO.getAll();
	}

}
