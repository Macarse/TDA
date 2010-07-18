package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.tda.model.Prueba;
import com.tda.presentation.client.PruebaServiceGWTWrapper;
import com.tda.service.PruebaService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class PruebaServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		PruebaServiceGWTWrapper {

	private PruebaService pruebaService;

	@Autowired
	@Required
	public void setPruebaService(PruebaService pruebaService) {
		this.pruebaService = pruebaService;
	}

	public void save(Prueba prueba) {
		System.out.println(prueba);
		pruebaService.save(prueba);
		
	}

	public void delete(Prueba prueba) {
		pruebaService.delete(prueba);
	}

	public Prueba getById(int id) {
		return pruebaService.getById(id);
	}

	public List<Prueba> getAll() {
		return pruebaService.getAll();
	}
}
