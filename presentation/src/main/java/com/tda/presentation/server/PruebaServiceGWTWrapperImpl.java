package com.tda.presentation.server;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tda.model.Prueba;
import com.tda.presentation.client.PruebaServiceGWTWrapper;
import com.tda.service.PruebaService;

@SuppressWarnings("serial")
public class PruebaServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		PruebaServiceGWTWrapper {

	@Autowired
	private PruebaService pruebaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
		pruebaService = (PruebaService) beanFactory.getBean("pruebaService");
	}

	@Required
	public void setPruebaService(PruebaService pruebaService) {
		this.pruebaService = pruebaService;
	}

	public void save(Prueba prueba) {
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
