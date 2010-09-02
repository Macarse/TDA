package com.tda.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.pediatrician.PediatricianForm;
import com.tda.persistence.dao.PediatricianFormDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.PediatricianFormService;

public class PediatricianFormServiceImpl implements PediatricianFormService {
	private PediatricianFormDAO pediatricianFormDAO;

	@Transactional
	public void save(PediatricianForm pediatricianForm) {
		if (pediatricianForm.getFillingDate() == null) {
			pediatricianForm.setFillingDate(new Date());
		}
		pediatricianFormDAO.save(pediatricianForm);
	}

	@Transactional
	public void delete(PediatricianForm pediatricianForm) {
		pediatricianFormDAO.delete(pediatricianForm);
	}

	@Transactional
	public void update(PediatricianForm pediatricianForm) {
		pediatricianFormDAO.update(pediatricianForm);

	}

	@Transactional(readOnly = true)
	public PediatricianForm findById(Long id) {
		return pediatricianFormDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<PediatricianForm> findAll() {
		return pediatricianFormDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<PediatricianForm> findAllPaged(Paginator paginator) {
		return pediatricianFormDAO.findAllPaged(paginator);
	}

	@Transactional(readOnly = true)
	public List<PediatricianForm> findByExample(
			PediatricianForm pediatricianForm) {
		return pediatricianFormDAO.findByExample(pediatricianForm);
	}

	public void setPediatricianFormDAO(PediatricianFormDAO pediatricianFormDAO) {
		this.pediatricianFormDAO = pediatricianFormDAO;
	}

}
