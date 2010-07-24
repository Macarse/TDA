package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.applicationuser.Authority;

public class AuthorityDAO extends GenericDAOImpl<Authority> {
	@Override
	protected Class<Authority> getDomainClass() {
		return Authority.class;
	}

	public List<Authority> findByAuthority(String authority) {
		Authority example = new Authority();
		example.setAuthority(authority);
		List<Authority> authorities = findByExample(example);

		return authorities;
	}
}
