package com.tda.presentation.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginServiceGWTWrapper extends RemoteService {

	boolean login(String user, String passwd);

}
