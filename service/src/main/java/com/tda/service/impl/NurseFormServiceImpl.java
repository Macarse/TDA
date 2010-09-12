package com.tda.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.nurse.NurseForm;
import com.tda.persistence.dao.NurseFormDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.NurseFormService;

public class NurseFormServiceImpl implements NurseFormService {
	private NurseFormDAO nurseFormDAO;

	@Transactional
	public void save(NurseForm nurseForm) {
		if (nurseForm.getFillingDate() == null) {
			nurseForm.setFillingDate(new Date());
		}
		nurseFormDAO.save(nurseForm);
	}

	@Transactional
	public void delete(NurseForm nurseForm) {
		nurseFormDAO.delete(nurseForm);
	}

	@Transactional
	public void update(NurseForm nurseForm) {
		nurseFormDAO.update(nurseForm);

	}

	@Transactional(readOnly = true)
	public NurseForm findById(Long id) {
		return nurseFormDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<NurseForm> findAll() {
		return nurseFormDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<NurseForm> findAllPaged(Paginator paginator) {
		return nurseFormDAO.findAllPaged(paginator);
	}

	@Transactional(readOnly = true)
	public List<NurseForm> findByExample(NurseForm nurseForm) {
		return nurseFormDAO.findByExample(nurseForm);
	}

	public void setNurseFormDAO(NurseFormDAO nurseFormDAO) {
		this.nurseFormDAO = nurseFormDAO;
	}

}
