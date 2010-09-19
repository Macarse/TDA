package com.tda.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.dentist.DentistForm;
import com.tda.persistence.dao.DentistFormDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.DentistFormService;

public class DentistFormServiceImpl implements DentistFormService {
	private DentistFormDAO dentistFormDAO;

	@Transactional
	public void save(DentistForm dentistForm) {
		if (dentistForm.getFillingDate() == null) {
			dentistForm.setFillingDate(new Date());
		}
		dentistFormDAO.save(dentistForm);
	}

	@Transactional
	public void delete(DentistForm dentistForm) {
		dentistFormDAO.delete(dentistForm);
	}

	@Transactional
	public void update(DentistForm dentistForm) {
		dentistFormDAO.update(dentistForm);

	}

	@Transactional(readOnly = true)
	public DentistForm findById(Long id) {
		return dentistFormDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<DentistForm> findAll() {
		return dentistFormDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<DentistForm> findAllPaged(Paginator paginator) {
		return dentistFormDAO.findAllPaged(paginator);
	}

	@Transactional(readOnly = true)
	public List<DentistForm> findByExample(DentistForm dentistForm) {
		return dentistFormDAO.findByExample(dentistForm);
	}

	public void setDentistFormDAO(DentistFormDAO dentistFormDAO) {
		this.dentistFormDAO = dentistFormDAO;
	}

}
