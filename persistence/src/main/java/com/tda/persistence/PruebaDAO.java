package com.tda.persistence;

import java.util.List;

import com.tda.model.Prueba;

public interface PruebaDAO {
	void save(Prueba prueba);
	
	void delete(Prueba prueba);
	
	Prueba getById(int id);
	
	List<Prueba> getAll();
}
