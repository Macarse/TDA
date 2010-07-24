package com.tda.persistence.dao;

import com.tda.model.applicationuser.ApplicationUser;

public class ApplicationUserDAO extends GenericDAOImpl<ApplicationUser> {

	@Override
	protected Class<ApplicationUser> getDomainClass() {
		return ApplicationUser.class;
	}

}
