package com.tda.persistence;

import java.util.List;

public interface GenericDAO<T> {
	void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    int count();

    List<T> findByExample(T exampleObject);
}
