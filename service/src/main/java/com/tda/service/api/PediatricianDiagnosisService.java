package com.tda.service.api;

import java.util.List;

import com.tda.model.pediatrician.PediatricianDiagnosis;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public interface PediatricianDiagnosisService {

	void save(PediatricianDiagnosis diagnosis);

	void delete(PediatricianDiagnosis diagnosis);

	void update(PediatricianDiagnosis diagnosis);

	PediatricianDiagnosis findById(Long id);

	List<PediatricianDiagnosis> findAll();

	void deleteById(Long id);

	int count();

	PediatricianDiagnosis findByName(String name)
			throws SingleResultExpectedException, NoDataFoundException;

	List<PediatricianDiagnosis> findByExample(PediatricianDiagnosis example);
}
