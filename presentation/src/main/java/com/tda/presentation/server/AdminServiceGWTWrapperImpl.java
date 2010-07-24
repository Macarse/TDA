package com.tda.presentation.server;

import com.tda.presentation.client.service.AdminServiceGWTWrapper;

@SuppressWarnings("serial")
public class AdminServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		AdminServiceGWTWrapper {

	public String hello() {
		return "hello";
	}


}
