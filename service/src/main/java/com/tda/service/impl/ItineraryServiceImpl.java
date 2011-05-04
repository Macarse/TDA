package com.tda.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.itinerary.Itinerary;
import com.tda.persistence.dao.ItineraryDAO;
import com.tda.service.api.ItineraryService;

public class ItineraryServiceImpl implements ItineraryService {
	private ItineraryDAO itineraryDAO;

	@Transactional
	public void save(Itinerary itinerary) {
		itineraryDAO.save(itinerary);

	}

	@Transactional
	public void delete(Itinerary itinerary) {
		itineraryDAO.delete(itinerary);

	}

	@Transactional
	public void update(Itinerary itinerary) {
		itineraryDAO.update(itinerary);

	}

	@Transactional(readOnly = true)
	public Itinerary getCurrent() {
		return getForSpecificDate(new Date());
	}

	@Transactional(readOnly = true)
	public Itinerary getForSpecificDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List<Itinerary> getAll() {
		return itineraryDAO.findAll();
	}

	public void setItineraryDAO(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}

	public ItineraryDAO getItineraryDAO() {
		return itineraryDAO;
	}

}
