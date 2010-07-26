package com.tda.presentation.server;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.presentation.client.service.LoginServiceGWTWrapper;
import com.tda.service.api.AuthenticationService;

@SuppressWarnings("serial")
public class LoginServiceGWTWrapperImpl extends
		AutoinjectingRemoteServiceServlet implements LoginServiceGWTWrapper {

	private AuthenticationService authenticationService;

	@Override
	protected void getBeansFromFactory(AutowireCapableBeanFactory beanFactory) {
		authenticationService = (AuthenticationService) beanFactory
				.getBean("authenticationService");
	}

	public boolean login(String username, String password) {
		return authenticationService.authenticate(username, password);
	}

	public void logout() {
		authenticationService.logout();
	}

}
