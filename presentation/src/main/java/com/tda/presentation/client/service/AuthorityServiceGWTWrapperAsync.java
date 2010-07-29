package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.applicationuser.Authority;

public interface AuthorityServiceGWTWrapperAsync extends
		CrudServiceGWTWrapperAsync<Authority> {

	void count(AsyncCallback<Integer> callback);

	void delete(Authority authority, AsyncCallback<Void> callback);

	void deleteById(Long id, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<Authority>> callback);

	void findByAuthority(String authority, AsyncCallback<Authority> callback);

	void findById(Long id, AsyncCallback<Authority> callback);

	void save(Authority authority, AsyncCallback<Void> callback);

	void update(Authority authority, AsyncCallback<Void> callback);

}
