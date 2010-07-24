package com.tda.presentation.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceGWTWrapperAsync {

	void login(String user, String passwd, AsyncCallback<Boolean> callback);

}
