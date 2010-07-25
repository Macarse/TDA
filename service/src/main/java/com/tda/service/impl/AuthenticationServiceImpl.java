package com.tda.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.tda.service.api.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {
	private AuthenticationManager authenticationManager;

	@Transactional(readOnly = true)
	public boolean authenticate(String username, String password) {
		boolean authenticated = false;

		try {
			Authentication authentication = getAuthenticationManager()
					.authenticate(
							new UsernamePasswordAuthenticationToken(username,
									password));
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			authenticated = authentication.isAuthenticated();
		} catch (AuthenticationException e) {
			authenticated = false;
		}
		return authenticated;
	}

	public void logout() {
		SecurityContextHolder.clearContext();

	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

}
