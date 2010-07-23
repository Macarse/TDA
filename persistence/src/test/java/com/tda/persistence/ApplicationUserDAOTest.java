package com.tda.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tda.model.ApplicationUser;
import com.tda.model.ApplicationUserBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml" })
public class ApplicationUserDAOTest {
	@Autowired
	ApplicationUserDAO applicationUserDAO;

	@Test
	public void testApplicationUserDAOSave() {
		ApplicationUser user = ApplicationUserBuilder.createApplicationUser()
				.withUsername("polaco").withPassword("pola")
				.withAuthority(authority).build();
	}
}
