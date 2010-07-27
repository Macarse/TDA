package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.applicationuser.ApplicationUser;
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

	public void save(ApplicationUser applicationUser) {
		applicationUserService.save(applicationUser);
	}

	public void delete(ApplicationUser applicationUser) {
		applicationUserService.delete(applicationUser);
	}

	public void update(ApplicationUser applicationUser) {
		applicationUserService.update(applicationUser);
	}

	public ApplicationUser findById(Long id) {
		return applicationUserService.findById(id);
	}

	public List<ApplicationUser> findAll() {
		return applicationUserService.findAll();
	}

	public void deleteById(Long id) {
		applicationUserService.deleteById(id);
	}

	public int count() {
		return applicationUserService.count();
	}

	public List<ApplicationUser> findByExample(ApplicationUser exampleObject) {
		return applicationUserService.findByExample(exampleObject);
	}

	public ApplicationUser findByUsername(String username) {
		return (ApplicationUser) applicationUserService
				.loadUserByUsername(username);
	}
}
