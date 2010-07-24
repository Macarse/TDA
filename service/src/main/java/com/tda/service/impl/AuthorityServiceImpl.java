package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.applicationuser.Authority;
import com.tda.persistence.dao.AuthorityDAO;
import com.tda.service.api.AuthorityService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public class AuthorityServiceImpl implements AuthorityService {
	private AuthorityDAO authorityDAO;

	@Transactional
	public void save(Authority authority) {
		authorityDAO.save(authority);
	}

	@Transactional
	public void delete(Authority authority) {
		authorityDAO.delete(authority);
	}

	@Transactional
	public void update(Authority authority) {
		authorityDAO.update(authority);
	}

	@Transactional(readOnly = true)
	public Authority findById(Long id) {
		return authorityDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Authority> findAll() {
		return authorityDAO.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		authorityDAO.deleteById(id);
	}

	@Transactional(readOnly = true)
	public int count() {
		return authorityDAO.count();
	}

	public void setAuthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

	@Transactional(readOnly = true)
	public Authority findByAuthority(String authority)
			throws SingleResultExpectedException, NoDataFoundException {

		List<Authority> authorities = authorityDAO.findByAuthority(authority);

		if (authorities.size() == 0)
			throw new NoDataFoundException("No authority found with name "
					+ authority);

		if (authorities.size() > 1)
			throw new SingleResultExpectedException(
					"Multiple authorities named " + authority);

		return authorities.get(0);
	}

}
