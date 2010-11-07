package com.tda.persistence.dao;

import com.tda.model.applicationuser.OnlineUser;

public class OnlineUserDAO extends GenericDAOImpl<OnlineUser> {

	@Override
	protected Class<OnlineUser> getDomainClass() {
		return OnlineUser.class;
	}
}
