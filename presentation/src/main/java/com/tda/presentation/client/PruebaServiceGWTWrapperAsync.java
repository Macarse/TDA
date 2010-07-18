package com.tda.presentation.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.Prueba;

public interface PruebaServiceGWTWrapperAsync {

	void delete(Prueba prueba, AsyncCallback<Void> callback);

	void getById(int id, AsyncCallback<Prueba> callback);

	void getAll(AsyncCallback<List<Prueba>> callback);

	void save(Prueba prueba, AsyncCallback<Void> callback);
}
