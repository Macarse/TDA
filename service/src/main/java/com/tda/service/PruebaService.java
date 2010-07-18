package com.tda.service;

import java.util.List;

import com.tda.model.Prueba;

public interface PruebaService {

	void save(Prueba prueba);
	
	void delete(Prueba prueba);
	
	Prueba getById(int id);
	
	List<Prueba> getAll();
}
