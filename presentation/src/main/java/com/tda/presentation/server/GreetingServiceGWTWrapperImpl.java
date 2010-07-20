package com.tda.presentation.server;

import com.tda.presentation.client.service.GreetingServiceGWTWrapper;
import com.tda.service.GreetingService;
import com.tda.service.GreetingServiceImpl;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		GreetingServiceGWTWrapper {

	/* TODO: Use DI */
	private GreetingService greetingService = new GreetingServiceImpl();

	public String greetServer(String name) throws IllegalArgumentException {
		return greetingService.greetServer(name);
	}

}
