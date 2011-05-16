package com.tda.persistence.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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

	public Itinerary findNextItinerary() {
		Date currentDate = new Date();
		List<Itinerary> currentItineraries = findItineraryForDate(currentDate);
		if (currentItineraries.size() > 0) {
			return currentItineraries.get(0);
		}

		DetachedCriteria crit = DetachedCriteria.forClass(Itinerary.class);
		crit.add(Restrictions.ge("beginningDate", currentDate));
		crit.addOrder(Order.asc("beginningDate"));
		List<Itinerary> nextItineraries = getHibernateTemplate()
				.findByCriteria(crit);
		if (nextItineraries.size() > 0) {
			return nextItineraries.get(0);
		}

		return null;
	}
}
