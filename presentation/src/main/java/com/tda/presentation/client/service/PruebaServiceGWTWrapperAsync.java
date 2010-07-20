package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.Prueba;

public interface PruebaServiceGWTWrapperAsync {

	void delete(Prueba prueba, AsyncCallback<Void> callback);

	void getById(Long id, AsyncCallback<Prueba> callback);

	void findAll(AsyncCallback<List<Prueba>> callback);

	void save(Prueba prueba, AsyncCallback<Void> callback);
}
