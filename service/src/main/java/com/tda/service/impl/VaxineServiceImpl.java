package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.nurse.Vaxine;
import com.tda.persistence.dao.VaxineDAO;
import com.tda.service.api.VaxineService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public class VaxineServiceImpl implements VaxineService {
	private VaxineDAO vaxineDAO;

	@Transactional
	public void save(Vaxine authority) {
		vaxineDAO.save(authority);
	}

	@Transactional
	public void delete(Vaxine authority) {
		vaxineDAO.delete(authority);
	}

	@Transactional
	public void update(Vaxine authority) {
		vaxineDAO.update(authority);
	}

	@Transactional(readOnly = true)
	public Vaxine findById(Long id) {
		return vaxineDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Vaxine> findAll() {
		return vaxineDAO.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		vaxineDAO.deleteById(id);
	}

	@Transactional(readOnly = true)
	public int count() {
		return vaxineDAO.count();
	}

	public void setVaxineDAO(VaxineDAO authorityDAO) {
		this.vaxineDAO = authorityDAO;
	}

	public Vaxine findByName(String name) throws SingleResultExpectedException,
			NoDataFoundException {
		Vaxine vaxine = new Vaxine();
		vaxine.setName(name);

		List<Vaxine> founded = vaxineDAO.findByExample(vaxine, false);

		if (founded.size() == 0)
			throw new NoDataFoundException("No vaxine found with name "
					+ founded);

		if (founded.size() > 1)
			throw new SingleResultExpectedException("Multiple vaxines named "
					+ founded);

		return founded.get(0);
	}

	public List<Vaxine> findByExample(Vaxine example) {
		return vaxineDAO.findByExample(example);
	}
}
