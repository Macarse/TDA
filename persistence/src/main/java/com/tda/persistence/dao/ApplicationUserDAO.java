package com.tda.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tda.applicationuser.ApplicationUser;

public class ApplicationUserDAO extends GenericDAOImpl<ApplicationUser> {

	@Override
	protected Class<ApplicationUser> getDomainClass() {
		return ApplicationUser.class;
	}

	@SuppressWarnings("unchecked")
	public List<ApplicationUser> findByUsername(String username) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(ApplicationUser.class);
		criteria.add(Restrictions.eq("username", username));
		return (List<ApplicationUser>) getHibernateTemplate().findByCriteria(
				criteria);

	}

}
