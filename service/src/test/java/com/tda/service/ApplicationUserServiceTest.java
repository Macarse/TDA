package com.tda.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.ApplicationUserBuilder;
import com.tda.service.api.ApplicationUserService;
import com.tda.service.api.AuthorityService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/spring-service.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationUserServiceTest {
	@Autowired
	private ApplicationUserService applicationUserService;
	@Autowired
	private AuthorityService authorityService;

	@Test
	public void loadValidUserByUsername() throws SingleResultExpectedException,
			NoDataFoundException {
		ApplicationUser applicationUser = ApplicationUserBuilder
				.createApplicationUser().withUsername("polaco123")
				.withAccountNonExpired().withAccountNonLocked()
				.withCredentialsNonExpired().withPassword("1234").enabled()
				.withAuthority(authorityService.findByAuthority("ROLE_ADMIN"))
				.build();

		applicationUserService.save(applicationUser);

		ApplicationUser loadedUser = (ApplicationUser) applicationUserService
				.loadUserByUsername(applicationUser.getUsername());

		assertEquals(applicationUser, loadedUser);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void loadInexistentUserByUsername() {
		applicationUserService.loadUserByUsername("noexiste");
	}

	@Test
	public void save() throws Exception {
		ApplicationUser user = ApplicationUserBuilder
				.createApplicationUser()
				.withUsername("polaco")
				.withPassword("pola")
				.withAuthority(authorityService.findByAuthority("ROLE_DENTIST"))
				.build();

		applicationUserService.save(user);
		assertEquals(user.getUsername(),
				applicationUserService.findById(user.getId()).getUsername());
	}

	@Test(expected = NoDataFoundException.class)
	public void saveUserWithInvalidRole() throws Exception {
		ApplicationUser user = ApplicationUserBuilder
				.createApplicationUser()
				.withUsername("polaco")
				.withPassword("pola")
				.withAuthority(
						authorityService.findByAuthority("NOEXISTEELROL"))
				.build();

		applicationUserService.save(user);
	}

}
