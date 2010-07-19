package com.tda.service;

import java.util.List;
import com.tda.model.Prueba;
import com.tda.persistence.PruebaDAO;

public class PruebaServiceImpl implements PruebaService {

	PruebaDAO pruebaDao;

	public void setPruebaDao(PruebaDAO pruebaDao) {
		this.pruebaDao = pruebaDao;
	}

	public void save(Prueba prueba) {
		pruebaDao.save(prueba);
	}

	public void delete(Prueba prueba) {
		pruebaDao.delete(prueba);
	}

	public void update(Prueba prueba) {
		pruebaDAO.update(prueba);
	}

	public Prueba findById(Long id) {
		return pruebaDAO.findById(id);
	}

	public List<Prueba> findAll() {
		return pruebaDAO.findAll();
	}
}
