package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.socialworker.SocialWorkerForm;
import com.tda.persistence.dao.SocialWorkerFormDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.SocialWorkerFormService;

public class SocialWorkerFormServiceImpl implements SocialWorkerFormService {
	private SocialWorkerFormDAO socialWorkerFormDAO;

	@Transactional
	public void save(SocialWorkerForm socialWorkerForm) {
		socialWorkerFormDAO.save(socialWorkerForm);
	}

	@Transactional
	public void delete(SocialWorkerForm socialWorkerForm) {
		socialWorkerFormDAO.delete(socialWorkerForm);
	}

	@Transactional
	public void update(SocialWorkerForm socialWorkerForm) {
		socialWorkerFormDAO.update(socialWorkerForm);

	}

	@Transactional(readOnly = true)
	public SocialWorkerForm findById(Long id) {
		return socialWorkerFormDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<SocialWorkerForm> findAll() {
		return socialWorkerFormDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<SocialWorkerForm> findAllPaged(Paginator paginator) {
		return socialWorkerFormDAO.findAllPaged(paginator);
	}

	@Transactional(readOnly = true)
	public List<SocialWorkerForm> findByExample(
			SocialWorkerForm socialWorkerForm) {
		return socialWorkerFormDAO.findByExample(socialWorkerForm);
	}

	public void setSocialWorkerFormDAO(SocialWorkerFormDAO socialWorkerFormDAO) {
		this.socialWorkerFormDAO = socialWorkerFormDAO;
	}

}
