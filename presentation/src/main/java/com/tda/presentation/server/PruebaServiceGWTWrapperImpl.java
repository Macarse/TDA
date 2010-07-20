package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.Prueba;
import com.tda.presentation.client.service.PruebaServiceGWTWrapper;
import com.tda.service.PruebaService;

@SuppressWarnings("serial")
public class PruebaServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		PruebaServiceGWTWrapper {

	private PruebaService pruebaService;

	@Override
	protected void getBeansFromFactory(AutowireCapableBeanFactory beanFactory) {
		pruebaService = (PruebaService) beanFactory.getBean("pruebaService");
	}

	public void setPruebaService(PruebaService pruebaService) {
		this.pruebaService = pruebaService;
	}

	public void save(Prueba prueba) {
		pruebaService.save(prueba);
		
	}

	public void delete(Prueba prueba) {
		pruebaService.delete(prueba);
	}

	public Prueba getById(Long id) {
		return pruebaService.findById(id);
	}

	public List<Prueba> findAll() {
		return pruebaService.findAll();
	}
}
