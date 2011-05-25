package com.tda.persistence.dao;

import com.tda.model.itinerary.Place;

public class PlaceDAO extends GenericDAOImpl<Place> {
	@Override
	protected Class<Place> getDomainClass() {
		return Place.class;
	}
}
