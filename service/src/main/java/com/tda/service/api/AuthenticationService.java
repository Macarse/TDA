package com.tda.service.api;

public interface AuthenticationService {

	boolean authenticate(String username, String password);

	void logout();

}
