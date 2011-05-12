package com.tda.persistence.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tda.model.itinerary.Itinerary;

public class ItineraryDAO extends GenericDAOImpl<Itinerary> {

	@Override
	protected Class<Itinerary> getDomainClass() {
		return Itinerary.class;
	}

	public List<Itinerary> findItineraryForDate(Date date) {
		DetachedCriteria crit = DetachedCriteria.forClass(Itinerary.class);
		crit.add(Restrictions.and(Restrictions.le("beginningDate", date),
				Restrictions.ge("endDate", date)));
		return getHibernateTemplate().findByCriteria(crit);
	}

}
