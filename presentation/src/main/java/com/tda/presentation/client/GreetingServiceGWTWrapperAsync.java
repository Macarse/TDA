package com.tda.presentation.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GreetingServiceGWTWrapperAsync {
	void greetServer(String name, AsyncCallback<String> callback);
}
