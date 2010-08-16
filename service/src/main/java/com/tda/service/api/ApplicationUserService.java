package com.tda.service.api;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.persistence.paginator.Paginator;

public interface ApplicationUserService extends UserDetailsService {

	void save(ApplicationUser applicationUser);

	void delete(ApplicationUser applicationUser);

	void update(ApplicationUser applicationUser);

	ApplicationUser findById(Long id);

	List<ApplicationUser> findAll();

	List<ApplicationUser> findAllPaged(Paginator paginator);

	void deleteById(Long id);

	int count();

	List<ApplicationUser> findByExample(ApplicationUser exampleObject);

	List<ApplicationUser> findByExamplePaged(ApplicationUser example,
			Paginator paginator);

}
