package com.tda.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.ApplicationUserBuilder;
import com.tda.persistence.dao.ApplicationUserDAO;
import com.tda.persistence.dao.AuthorityDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationUserDAOTest {
	@Autowired
	ApplicationUserDAO applicationUserDAO;
	@Autowired
	AuthorityDAO authorityDAO;

	@Test
	public void save() throws Exception {
		ApplicationUser user = ApplicationUserBuilder.createApplicationUser()
				.withUsername("polaco").withPassword("pola")
				.withAuthority(authorityDAO.findByAuthority("ROLE_DENTIST"))
				.build();

		applicationUserDAO.save(user);
		assertEquals(user.getUsername(),
				applicationUserDAO.findById(user.getId()).getUsername());
	}

	@Test(expected = Exception.class)
	public void saveUserWithInvalidRole() throws Exception {
		ApplicationUser user = ApplicationUserBuilder.createApplicationUser()
				.withUsername("polaco").withPassword("pola")
				.withAuthority(authorityDAO.findByAuthority("NOEXISTEELROL"))
				.build();

		applicationUserDAO.save(user);
	}
}
