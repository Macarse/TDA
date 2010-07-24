package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.applicationuser.Authority;

public class AuthorityDAO extends GenericDAOImpl<Authority> {
	@Override
	protected Class<Authority> getDomainClass() {
		return Authority.class;
	}

	public Authority findByAuthority(String authority) throws Exception {
		Authority example = new Authority();
		example.setAuthority(authority);
		List<Authority> authorities = findByExample(example);

		if (authorities.size() != 1)
			throw new Exception("None or multiple authorities found for "
					+ authority);

		return authorities.get(0);
	}
}
