package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.applicationuser.Authority;
import com.tda.presentation.client.service.AuthorityServiceGWTWrapper;
import com.tda.service.api.AuthorityService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

@SuppressWarnings("serial")
public class AuthorityServiceGWTWrapperImpl extends
		AutoinjectingRemoteServiceServlet implements AuthorityServiceGWTWrapper {

	private AuthorityService authorityService;

	@Override
	protected void getBeansFromFactory(AutowireCapableBeanFactory beanFactory) {
		authorityService = (AuthorityService) beanFactory
				.getBean("authorityService");
	}

	public void save(Authority authority) {
		authorityService.save(authority);
	}

	public void delete(Authority authority) {
		authorityService.delete(authority);
	}

	public void update(Authority authority) {
		authorityService.update(authority);
	}

	public Authority findById(Long id) {
		return authorityService.findById(id);
	}

	public Authority findByAuthority(String authority) {
		/* TODO: Exceptions are not serializables */
		try {
			return authorityService.findByAuthority(authority);
		} catch (SingleResultExpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Authority> findAll() {
		return authorityService.findAll();
	}

	public void deleteById(Long id) {
		authorityService.deleteById(id);
	}

	public int count() {
		return authorityService.count();
	}
}
