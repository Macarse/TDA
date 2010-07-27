package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.model.applicationuser.ApplicationUser;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("applicationUser")
public interface ApplicationUserServiceGWTWrapper extends RemoteService {

	void save(ApplicationUser applicationUser);

	void delete(ApplicationUser applicationUser);

	void update(ApplicationUser applicationUser);

	ApplicationUser findById(Long id);

	List<ApplicationUser> findAll();

	void deleteById(Long id);

	int count();

	List<ApplicationUser> findByExample(ApplicationUser exampleObject);

	ApplicationUser findByUsername(String username);
}
