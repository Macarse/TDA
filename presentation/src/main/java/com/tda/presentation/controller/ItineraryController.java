package com.tda.presentation.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.itinerary.Itinerary;
import com.tda.model.itinerary.Place;
import com.tda.service.api.ItineraryService;

@Controller
@RequestMapping(value = "/itinerary")
@SessionAttributes({ "currentItinerary" })
public class ItineraryController {
	private ItineraryService itineraryService;

	@Autowired
	public void setItineraryService(ItineraryService itineraryService) {
		this.itineraryService = itineraryService;
	}

	@ModelAttribute("itineraryForm")
	public Itinerary getItineraryCommand(Model model) {
		Itinerary itineraryForm = new Itinerary();
		itineraryForm.setPlaces(new AutoPopulatingList<Place>(Place.class));
		return itineraryForm;
	}

	@ModelAttribute("currentItinerary")
	public Itinerary getCurrent() {
		return itineraryService.getNext();
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
	protected ModelAndView create(Model model,
			@Valid @ModelAttribute("itineraryForm") Itinerary itineraryForm,
			@ModelAttribute("currentItinerary") Itinerary currentItinerary,
			BindingResult result, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (result.hasErrors()) {
			modelAndView.setViewName("itinerary/createForm");
		} else {
			itineraryService.save(itineraryForm);
			modelAndView.setViewName("itinerary/resultForm");
			modelAndView.addObject("savedClass", itineraryForm);
		}

		return modelAndView;
	}
}