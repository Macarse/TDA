package com.tda.presentation.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.Authority;
import com.tda.service.api.ApplicationUserService;

public class CommonController {
	protected ApplicationUserService applicationUserService;
	
	@Autowired
	public void setApplicationUserService(
			ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}
	
	@ModelAttribute("userList")
	public List<ApplicationUser> getUserList() {
		List<ApplicationUser> users = applicationUserService.findAll();

		// set admin authority
		Authority adminAuth = new Authority();
		adminAuth.setAuthority("ROLE_ADMIN");

		// ROLE_USER
		Authority userAuth = new Authority();
		userAuth.setAuthority("ROLE_USER");

		Iterator<ApplicationUser> iter = users.iterator();
		ApplicationUser user;

		while (iter.hasNext()) {
			user = iter.next();
			Collection<Authority> auths = user.getMyAuthorities();

			if (auths.size() == 2 && auths.contains(adminAuth)
					&& auths.contains(userAuth)) {
				iter.remove();
			}
		}

		return users;
	}
}
