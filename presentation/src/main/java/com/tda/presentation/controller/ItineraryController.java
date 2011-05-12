package com.tda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tda.model.itinerary.ItineraryForm;
import com.tda.model.itinerary.Place;

@Controller
@RequestMapping(value = "/itinerary")
public class ItineraryController {

	@ModelAttribute("itineraryForm")
	public ItineraryForm getItineraryCommand(Model model) {
		ItineraryForm itineraryForm = new ItineraryForm();
		itineraryForm.setPlaces(new AutoPopulatingList<Place>(Place.class));
		return itineraryForm;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String getForm() {
		return "itinerary/createForm";
	}

	@RequestMapping(method = RequestMethod.GET, value = "appendPlace")
	protected String appendPlaceField(@RequestParam Integer fieldId,
			ModelMap model) {
		model.addAttribute("placeNumber", fieldId);
		return "itinerary/addPlace";
	}

	@RequestMapping(method = RequestMethod.POST, value = "add")
	protected String onSubmit(
			@ModelAttribute("itineraryForm") ItineraryForm itineraryForm,
			ModelMap model) {
		model.addAttribute("savedClass", itineraryForm);
		return "itinerary/resultForm";

	}
}
