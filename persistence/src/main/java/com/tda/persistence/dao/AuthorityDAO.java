package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.applicationuser.Authority;
import com.tda.persistence.exception.NoDataFoundException;
import com.tda.persistence.exception.SingleResultExpectedException;

public class AuthorityDAO extends GenericDAOImpl<Authority> {
	@Override
	protected Class<Authority> getDomainClass() {
		return Authority.class;
	}

	public Authority findByAuthority(String authority)
			throws SingleResultExpectedException, NoDataFoundException {
		Authority example = new Authority();
		example.setAuthority(authority);
		List<Authority> authorities = findByExample(example);

		if (authorities.size() == 0)
			throw new NoDataFoundException("No authority found for name "
					+ authority);

		if (authorities.size() > 1)
			throw new SingleResultExpectedException(
					"Multiple authorities found for " + authority);

		return authorities.get(0);
	}
}
