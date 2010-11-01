package com.tda.service.api;

import java.util.List;

import com.tda.model.nurse.Vaxine;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public interface VaxineService {

	void save(Vaxine authority);

	void delete(Vaxine authority);

	void update(Vaxine authority);

	Vaxine findById(Long id);

	List<Vaxine> findAll();

	void deleteById(Long id);

	int count();

	Vaxine findByName(String name) throws SingleResultExpectedException,
			NoDataFoundException;

	List<Vaxine> findByExample(Vaxine example);
}
