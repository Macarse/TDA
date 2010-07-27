package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.applicationuser.ApplicationUserGWT;

public interface ApplicationUserServiceGWTWrapperAsync {

	void count(AsyncCallback<Integer> callback);

	void delete(ApplicationUserGWT applicationUser, AsyncCallback<Void> callback);

	void deleteById(Long id, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<ApplicationUserGWT>> callback);

	void findByExample(ApplicationUserGWT exampleObject,
			AsyncCallback<List<ApplicationUserGWT>> callback);

	void findById(Long id, AsyncCallback<ApplicationUserGWT> callback);

	void findByUsername(String username,
			AsyncCallback<ApplicationUserGWT> callback);

	void save(ApplicationUserGWT applicationUser, AsyncCallback<Void> callback);

	void update(ApplicationUserGWT applicationUser, AsyncCallback<Void> callback);

}
