package com.tda.service.api;

import java.util.Date;
import java.util.List;

import com.tda.model.itinerary.Itinerary;

public interface ItineraryService {
	void save(Itinerary itinerary);

	void delete(Itinerary itinerary);

	void update(Itinerary itinerary);

	List<Itinerary> getAll();

	Itinerary getCurrent();

	Itinerary getForSpecificDate(Date date);
}
