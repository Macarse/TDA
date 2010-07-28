package com.tda.presentation.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.applicationuser.ApplicationUser;
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

	public void save(ApplicationUserGWT applicationUserGWT) {
		applicationUserService.save(wrap(applicationUserGWT));
	}

	public void delete(ApplicationUserGWT applicationUserGWT) {
		applicationUserService.delete(wrap(applicationUserGWT));
	}

	public void update(ApplicationUserGWT applicationUserGWT) {
		applicationUserService.update(wrap(applicationUserGWT));
	}

	public ApplicationUserGWT findById(Long id) {
		return wrap(applicationUserService.findById(id));
	}

	public List<ApplicationUserGWT> findAll() {
		return wrap(applicationUserService.findAll());
	}

	public void deleteById(Long id) {
		applicationUserService.deleteById(id);
	}

	public int count() {
		return applicationUserService.count();
	}

	public List<ApplicationUserGWT> findByExample(
			ApplicationUserGWT exampleObject) {
		return wrap(applicationUserService.findByExample(wrap(exampleObject)));
	}

	public ApplicationUserGWT findByUsername(String username) {
		return wrap((ApplicationUser) applicationUserService
				.loadUserByUsername(username));
	}

	private static List<ApplicationUserGWT> wrap(List<ApplicationUser> from) {
		List<ApplicationUserGWT> ret = new ArrayList<ApplicationUserGWT>();

		for (ApplicationUser applicationUser : from) {
			ret.add(wrap(applicationUser));
		}

		return ret;
	}

	private static ApplicationUser wrap(ApplicationUserGWT from) {

		ApplicationUser to = new ApplicationUser();

		to.setId(from.getId());
		to.setUsername(from.getUsername());
		to.setPassword(from.getPassword());
		to.setAccountNonExpired(from.isAccountNonExpired());
		to.setAccountNonLocked(from.isAccountNonLocked());
		to.setCredentialsNonExpired(from.isCredentialsNonExpired());
		to.setEnabled(from.isEnabled());
		to.setMyAuthorities(from.getMyAuthorities());

		return to;
	}

	private static ApplicationUserGWT wrap(ApplicationUser from) {

		ApplicationUserGWT to = new ApplicationUserGWT();

		to.setId(from.getId());
		to.setUsername(from.getUsername());
		to.setPassword(from.getPassword());
		to.setAccountNonExpired(from.isAccountNonExpired());
		to.setAccountNonLocked(from.isAccountNonLocked());
		to.setCredentialsNonExpired(from.isCredentialsNonExpired());
		to.setEnabled(from.isEnabled());
		to.setMyAuthorities(from.getMyAuthorities());

		return to;
	}
}
