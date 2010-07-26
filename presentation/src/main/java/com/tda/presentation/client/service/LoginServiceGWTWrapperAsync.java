package com.tda.presentation.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceGWTWrapperAsync {

	void login(String username, String password, AsyncCallback<Boolean> callback);

	void logout(AsyncCallback<Void> callback);

}
