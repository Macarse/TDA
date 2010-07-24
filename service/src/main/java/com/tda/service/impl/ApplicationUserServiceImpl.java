package com.tda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.ApplicationUserBuilder;
import com.tda.persistence.dao.ApplicationUserDAO;
import com.tda.service.api.ApplicationUserService;

public class ApplicationUserServiceImpl implements ApplicationUserService {
	@Autowired
	ApplicationUserDAO applicationUserDAO;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		ApplicationUser example = ApplicationUserBuilder
				.createApplicationUser().withUsername(username).build();
		List<ApplicationUser> usersFound = applicationUserDAO
				.findByExample(example);

		if (usersFound.size() == 0)
			throw new UsernameNotFoundException("User: " + username
					+ " not found");

		return usersFound.get(0);
	}
}
