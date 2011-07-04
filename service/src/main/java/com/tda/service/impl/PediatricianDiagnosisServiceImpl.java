package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.pediatrician.PediatricianDiagnosis;
import com.tda.persistence.dao.PediatricianDiagnosisDAO;
import com.tda.service.api.PediatricianDiagnosisService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public class PediatricianDiagnosisServiceImpl implements
		PediatricianDiagnosisService {
	private PediatricianDiagnosisDAO pediatricianDiagnosisDAO;

	@Transactional
	public void save(PediatricianDiagnosis diagnosis) {
		pediatricianDiagnosisDAO.save(diagnosis);
	}

	@Transactional
	public void delete(PediatricianDiagnosis diagnosis) {
		pediatricianDiagnosisDAO.delete(diagnosis);
	}

	@Transactional
	public void update(PediatricianDiagnosis diagnosis) {
		pediatricianDiagnosisDAO.update(diagnosis);
	}

	@Transactional(readOnly = true)
	public PediatricianDiagnosis findById(Long id) {
		return pediatricianDiagnosisDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<PediatricianDiagnosis> findAll() {
		return pediatricianDiagnosisDAO.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		pediatricianDiagnosisDAO.deleteById(id);
	}

	@Transactional(readOnly = true)
	public int count() {
		return pediatricianDiagnosisDAO.count();
	}

	public void setPediatricianDiagnosisDAO(
			PediatricianDiagnosisDAO pediatricianDiagnosisDAO) {
		this.pediatricianDiagnosisDAO = pediatricianDiagnosisDAO;
	}

	public PediatricianDiagnosis findByName(String name)
			throws SingleResultExpectedException, NoDataFoundException {
		PediatricianDiagnosis diagnosis = new PediatricianDiagnosis();
		diagnosis.setName(name);

		List<PediatricianDiagnosis> founded = pediatricianDiagnosisDAO
				.findByExample(diagnosis, false);

		if (founded.size() == 0)
			throw new NoDataFoundException("No vaxine found with name "
					+ founded);

		if (founded.size() > 1)
			throw new SingleResultExpectedException("Multiple vaxines named "
					+ founded);

		return founded.get(0);
	}

	public List<PediatricianDiagnosis> findByExample(
			PediatricianDiagnosis example) {
		return pediatricianDiagnosisDAO.findByExample(example);
	}
}
