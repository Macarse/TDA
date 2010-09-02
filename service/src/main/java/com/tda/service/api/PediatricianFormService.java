package com.tda.service.api;

import java.util.List;

import com.tda.model.pediatrician.PediatricianForm;
import com.tda.persistence.paginator.Paginator;

public interface PediatricianFormService {
	void save(PediatricianForm pediatricianForm);

	void delete(PediatricianForm pediatricianForm);

	void update(PediatricianForm pediatricianForm);

	PediatricianForm findById(Long id);

	List<PediatricianForm> findAll();

	List<PediatricianForm> findAllPaged(Paginator paginator);

	List<PediatricianForm> findByExample(PediatricianForm pediatricianForm);
}
