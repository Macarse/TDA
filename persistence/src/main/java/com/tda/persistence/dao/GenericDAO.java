package com.tda.persistence.dao;

import java.util.List;

import com.tda.persistence.paginator.Paginator;

public interface GenericDAO<T> {
	void save(T entity);

	void delete(T entity);

	void update(T entity);

	T findById(Long id);

	List<T> findAll();

	List<T> findAllPaged(Paginator paginator);

	void deleteById(Long id);

	int count();

	List<T> findByExample(T exampleObject);

	List<T> findByExamplePaged(T exampleObject, Paginator paginator);
}
