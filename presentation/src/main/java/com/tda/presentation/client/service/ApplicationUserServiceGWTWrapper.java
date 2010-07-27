package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.model.applicationuser.ApplicationUserGWT;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("applicationUser")
public interface ApplicationUserServiceGWTWrapper extends RemoteService {

	void save(ApplicationUserGWT applicationUser);

	void delete(ApplicationUserGWT applicationUser);

	void update(ApplicationUserGWT applicationUser);

	ApplicationUserGWT findById(Long id);

	List<ApplicationUserGWT> findAll();

	void deleteById(Long id);

	int count();

	List<ApplicationUserGWT> findByExample(ApplicationUserGWT exampleObject);

	ApplicationUserGWT findByUsername(String username);
}
