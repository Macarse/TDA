package com.tda.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.Authority;
import com.tda.persistence.dao.ApplicationUserDAO;
import com.tda.persistence.dao.AuthorityDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.ApplicationUserService;

public class ApplicationUserServiceImpl implements ApplicationUserService,
		UserDetailsService {
	private ApplicationUserDAO applicationUserDAO;
	private AuthorityDAO authorityDAO;

	public ApplicationUserDAO getApplicationUserDAO() {
		return applicationUserDAO;
	}

	public void setApplicationUserDAO(ApplicationUserDAO applicationUserDAO) {
		this.applicationUserDAO = applicationUserDAO;
	}

	public AuthorityDAO getAuthorityDAO() {
		return authorityDAO;
	}

	public void setAuthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

	@Transactional
	public void save(ApplicationUser applicationUser) {
		// En el save hasheo
		applicationUser.setPassword(HashService.getHash(applicationUser
				.getPassword()));
		applicationUser.setConfirmPassword(HashService.getHash(applicationUser
				.getConfirmPassword()));
		applicationUserDAO.save(applicationUser);
	}

	@Transactional
	public void delete(ApplicationUser applicationUser) {
		applicationUserDAO.delete(applicationUser);
	}

	@Transactional
	public void update(ApplicationUser applicationUser) {
		// En el update hasheo, pero tengo que asegurarme que se cambio
		// la pwd, que no es el hash, sino texto plano
		if (applicationUser.getPassword().length() > 8) {
			applicationUser.setPassword(HashService.getHash(applicationUser
					.getPassword()));
			applicationUser.setConfirmPassword(HashService
					.getHash(applicationUser.getConfirmPassword()));
		}
		applicationUserDAO.update(applicationUser);
	}

	@Transactional(readOnly = true)
	public ApplicationUser findById(Long id) {
		return applicationUserDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<ApplicationUser> findAll() {
		return applicationUserDAO.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		applicationUserDAO.deleteById(id);
	}

	@Transactional(readOnly = true)
	public int count() {
		return applicationUserDAO.count();
	}

	@Transactional(readOnly = true)
	public List<ApplicationUser> findByExample(ApplicationUser exampleObject) {
		List<ApplicationUser> list = applicationUserDAO
				.findByExample(exampleObject);
		return filterByAuthority(list, exampleObject.getMyAuthorities());
	}

	public List<ApplicationUser> findAllPaged(Paginator paginator) {
		return applicationUserDAO.findAllPaged(paginator);
	}

	public List<ApplicationUser> findByExamplePaged(ApplicationUser example,
			Paginator paginator) {
		List<ApplicationUser> list = applicationUserDAO.findByExamplePaged(
				example, paginator);

		return filterByAuthority(list, example.getMyAuthorities());
	}

	public List<ApplicationUser> findByExample(ApplicationUser exampleObject,
			List<String> excludedFields) {
		List<ApplicationUser> list = applicationUserDAO.findByExample(
				exampleObject, excludedFields);

		return filterByAuthority(list, exampleObject.getMyAuthorities());
	}

	public List<ApplicationUser> findByExamplePaged(ApplicationUser example,
			Paginator paginator, List<String> excludedFields) {
		List<ApplicationUser> list = applicationUserDAO.findByExamplePaged(
				example, paginator, excludedFields);

		return filterByAuthority(list, example.getMyAuthorities());

	}

	private List<ApplicationUser> filterByAuthority(
			List<ApplicationUser> originalList,
			Collection<Authority> authorityList) {

		// TODO this method should be erased, the filter by authority should be
		// done by
		// hibernate in the findByExample

		List<ApplicationUser> authorityFilteredList = new ArrayList<ApplicationUser>(
				originalList);

		if (authorityList != null && authorityList.size() != 0) {
			for (ApplicationUser applicationUser : originalList) {
				if (!applicationUser.getMyAuthorities().containsAll(
						authorityList)) {
					authorityFilteredList.remove(applicationUser);
				}
			}
		}

		return authorityFilteredList;
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		List<ApplicationUser> usersFound = applicationUserDAO
				.findByUsername(username);

		if (usersFound.size() == 0)
			throw new UsernameNotFoundException("User: " + username
					+ " not found");

		return usersFound.get(0);
	}
}
