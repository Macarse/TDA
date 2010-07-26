package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CrudServiceGWTWrapperAsync<T> {

	void delete(T item, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<T>> callback);

	void findById(Long id, AsyncCallback<T> callback);

	void save(T item, AsyncCallback<Void> callback);

	void update(T item, AsyncCallback<Void> callback);

}
