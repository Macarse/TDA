package com.tda.service.api;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tda.model.applicationuser.ApplicationUser;

public interface ApplicationUserService extends UserDetailsService {

	void save(ApplicationUser applicationUser);

	void delete(ApplicationUser applicationUser);

	void update(ApplicationUser applicationUser);

	ApplicationUser findById(Long id);

	List<ApplicationUser> findAll();

	void deleteById(Long id);

	int count();

	List<ApplicationUser> findByExample(ApplicationUser exampleObject);

}
