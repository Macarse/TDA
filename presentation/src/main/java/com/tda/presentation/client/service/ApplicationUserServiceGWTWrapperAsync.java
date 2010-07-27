package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.applicationuser.ApplicationUser;

public interface ApplicationUserServiceGWTWrapperAsync {

	void count(AsyncCallback<Integer> callback);

	void delete(ApplicationUser applicationUser, AsyncCallback<Void> callback);

	void deleteById(Long id, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<ApplicationUser>> callback);

	void findByExample(ApplicationUser exampleObject,
			AsyncCallback<List<ApplicationUser>> callback);

	void findById(Long id, AsyncCallback<ApplicationUser> callback);

	void findByUsername(String username, AsyncCallback<ApplicationUser> callback);

	void save(ApplicationUser applicationUser, AsyncCallback<Void> callback);

	void update(ApplicationUser applicationUser, AsyncCallback<Void> callback);

}
