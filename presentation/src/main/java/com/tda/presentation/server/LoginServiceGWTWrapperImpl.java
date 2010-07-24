package com.tda.presentation.server;

import com.tda.presentation.client.service.LoginServiceGWTWrapper;

@SuppressWarnings("serial")
public class LoginServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		LoginServiceGWTWrapper {

	public boolean login(String user, String passwd) {
		return true;
	}

}
