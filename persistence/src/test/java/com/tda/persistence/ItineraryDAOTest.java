package com.tda.persistence;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.itinerary.Itinerary;
import com.tda.model.itinerary.Place;
import com.tda.persistence.dao.ItineraryDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/test-datasource.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ItineraryDAOTest {
	@Autowired
	ItineraryDAO itineraryDAO;

	@Before
	public void setUp() {
		List<Place> places = new ArrayList<Place>();
		Place somePlace = new Place();
		somePlace.setArrivalDate(new Date(12000));
		somePlace.setCity("A city");
		places.add(somePlace);

		Itinerary itinerary = new Itinerary();
		itinerary.setBeginningDate(new Date(10000));
		itinerary.setEndDate(new Date(25000));
		itineraryDAO.save(itinerary);

		Itinerary anotherItinerary = new Itinerary();
		anotherItinerary.setBeginningDate(new Date(100000));
		anotherItinerary.setEndDate(new Date(150000));
		itineraryDAO.save(anotherItinerary);
	}

	@Test
	public void findForValidDate() {
		List<Itinerary> itineraries = itineraryDAO
				.findItineraryForDate(new Date(20000));
		assertEquals(1, itineraries.size());
	}

	@Test
	public void findForInvalidDate() {
		List<Itinerary> itineraries = itineraryDAO
				.findItineraryForDate(new Date());
		assertEquals(0, itineraries.size());
	}

	@Test
	public void getItineraryListShouldReturnAllTheSaved() {
		List<Itinerary> retrieved = itineraryDAO.findAll();
		assertEquals(2, retrieved.size());
	}
}
