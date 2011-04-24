package com.tda.persistence.dao;

import com.tda.model.itinerary.Itinerary;

public class ItineraryDAO extends GenericDAOImpl<Itinerary> {

	@Override
	protected Class<Itinerary> getDomainClass() {
		return Itinerary.class;
	}

}
