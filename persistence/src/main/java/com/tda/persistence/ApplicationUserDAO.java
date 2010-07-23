package com.tda.persistence;

import com.tda.model.ApplicationUser;

public class ApplicationUserDAO extends GenericDAOImpl<ApplicationUser> {

	@Override
	protected Class<ApplicationUser> getDomainClass() {
		return ApplicationUser.class;
	}

}
