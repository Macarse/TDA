package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.paginator.Paginator;

public interface GenericDAO<T> {
	void save(T entity);

	void delete(T entity);

	void update(T entity);

	T findById(Long id);

	List<T> findAll();

	void deleteById(Long id);

	int count();

	List<T> findByExample(T exampleObject);

	List<T> findAllPaged(Paginator paginator);
}
