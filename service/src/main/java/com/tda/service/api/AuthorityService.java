package com.tda.service.api;

import java.util.List;

import com.tda.model.applicationuser.Authority;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public interface AuthorityService {
	void save(Authority authority);

	void delete(Authority authority);

	void update(Authority authority);

	Authority findById(Long id);

	Authority findByAuthority(String authority)
			throws SingleResultExpectedException, NoDataFoundException;

	List<Authority> findAll();

	void deleteById(Long id);

	int count();
}
