package com.tda.presentation.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tda.presentation.client.GreetingServiceGWTWrapper;
import com.tda.service.GreetingService;
import com.tda.service.GreetingServiceImpl;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceGWTWrapperImpl extends RemoteServiceServlet implements
		GreetingServiceGWTWrapper {

	private GreetingService greetingService = new GreetingServiceImpl();

	public String greetServer(String name) throws IllegalArgumentException {
		return greetingService.greetServer(name);
	}

}
