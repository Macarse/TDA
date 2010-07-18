package com.tda.presentation.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Expose Spring services to GWT app
 * From http://pgt.de/2009/07/17/non-invasive-gwt-and-spring-integration-reloaded/
 */
public abstract class AutoinjectingRemoteServiceServlet extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
	}

}
