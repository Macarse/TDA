package com.tda.service.api;

import java.util.Date;

import com.tda.model.itinerary.Itinerary;

public interface ItineraryService {
	void save(Itinerary itinerary);

	void delete(Itinerary itinerary);

	void update(Itinerary itinerary);

	Itinerary getCurrent();

	Itinerary getForSpecificDate(Date date);
}
