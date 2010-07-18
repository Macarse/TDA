package com.tda.presentation.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.model.Prueba;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("prueba")
public interface PruebaServiceGWTWrapper extends RemoteService {

	void save(Prueba prueba);
	
	void delete(Prueba prueba);
	
	Prueba getById(int id);
	
	List<Prueba> getAll();
}
