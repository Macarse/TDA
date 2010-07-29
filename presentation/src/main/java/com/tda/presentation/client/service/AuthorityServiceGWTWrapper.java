package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.model.applicationuser.Authority;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("patient")
public interface AuthorityServiceGWTWrapper extends RemoteService {

	void save(Authority authority);

	void delete(Authority authority);

	void update(Authority authority);

	Authority findById(Long id);

	Authority findByAuthority(String authority);

	List<Authority> findAll();

	void deleteById(Long id);

	int count();
}
