package com.tda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tda.model.Prueba;
import com.tda.persistence.PruebaDAO;

public class PruebaServiceImpl implements PruebaService {
	@Autowired
	PruebaDAO pruebaDAO;

	public void save(Prueba prueba) {
		pruebaDAO.save(prueba);
	}

	public void delete(Prueba prueba) {
		pruebaDAO.delete(prueba);
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
