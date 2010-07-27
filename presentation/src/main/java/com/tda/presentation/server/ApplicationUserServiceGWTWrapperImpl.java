package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.applicationuser.ApplicationUserGWT;
import com.tda.presentation.client.service.ApplicationUserServiceGWTWrapper;
import com.tda.service.api.ApplicationUserService;

@SuppressWarnings("serial")
public class ApplicationUserServiceGWTWrapperImpl extends
		AutoinjectingRemoteServiceServlet implements
		ApplicationUserServiceGWTWrapper {

	private ApplicationUserService applicationUserService;

	@Override
	protected void getBeansFromFactory(AutowireCapableBeanFactory beanFactory) {
		applicationUserService = (ApplicationUserService) beanFactory
				.getBean("applicationUserService");
	}

	/* TODO: FIX this class! */

	public void save(ApplicationUserGWT applicationUser) {
		// applicationUserService.save(applicationUser);
	}

	public void delete(ApplicationUserGWT applicationUser) {
		// applicationUserService.delete(applicationUser);
	}

	public void update(ApplicationUserGWT applicationUser) {
		// applicationUserService.update(applicationUser);
	}

	public ApplicationUserGWT findById(Long id) {
		return applicationUserService.findById(id);
	}

	public List<ApplicationUserGWT> findAll() {
		// return applicationUserService.findAll();
		return null;
	}

	public void deleteById(Long id) {
		applicationUserService.deleteById(id);
	}

	public int count() {
		return applicationUserService.count();
	}

	public List<ApplicationUserGWT> findByExample(
			ApplicationUserGWT exampleObject) {
		// return applicationUserService.findByExample(exampleObject);
		return null;
	}

	public ApplicationUserGWT findByUsername(String username) {
		return (ApplicationUserGWT) applicationUserService
				.loadUserByUsername(username);
	}
}
