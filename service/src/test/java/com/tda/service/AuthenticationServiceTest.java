package com.tda.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.applicationuser.ApplicationUser;
import com.tda.applicationuser.ApplicationUserBuilder;
import com.tda.service.api.ApplicationUserService;
import com.tda.service.api.AuthenticationService;
import com.tda.service.api.AuthorityService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/spring-service.xml", "classpath:/spring-security.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AuthenticationServiceTest {
	@Autowired
	private ApplicationUserService applicationUserService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private AuthenticationService authenticationService;

	@Test
	public void validAuthentication() throws SingleResultExpectedException,
			NoDataFoundException {

		ApplicationUser applicationUser = ApplicationUserBuilder
				.createApplicationUser().withUsername("polaco123")
				.withAccountNonExpired().withAccountNonLocked()
				.withCredentialsNonExpired().withPassword("1234").enabled()
				.withAuthority(authorityService.findByAuthority("ROLE_ADMIN"))
				.build();

		applicationUserService.save(applicationUser);

		assertTrue(authenticationService.authenticate("polaco123", "1234"));
	}

	@Test
	public void invalidAuthentication() throws SingleResultExpectedException,
			NoDataFoundException {

		ApplicationUser applicationUser = ApplicationUserBuilder
				.createApplicationUser().withUsername("polaco123")
				.withAccountNonExpired().withAccountNonLocked()
				.withCredentialsNonExpired().withPassword("1234").enabled()
				.withAuthority(authorityService.findByAuthority("ROLE_ADMIN"))
				.build();

		applicationUserService.save(applicationUser);

		assertFalse(authenticationService.authenticate("blabla", "12345"));
	}

}
