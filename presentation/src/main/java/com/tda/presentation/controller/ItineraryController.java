package com.tda.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	private List<Integer> removedIndexes = new ArrayList<Integer>();

	@Autowired
	public void setItineraryService(ItineraryService itineraryService) {
		this.itineraryService = itineraryService;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String getForm(Model model) {
		Itinerary itineraryForm = new Itinerary();
		itineraryForm.setPlaces(new AutoPopulatingList<Place>(Place.class));
		model.addAttribute("itineraryForm", itineraryForm);
		return "itinerary/createForm";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String getEditForm(@PathVariable Long id, Model model) {
		Itinerary itinerary = itineraryService.getById(id);

		if (itinerary != null) {
			model.addAttribute("placeSize", itinerary.getPlaces().size());
			itinerary.setPlaces(new AutoPopulatingList<Place>(itinerary
					.getPlaces(), Place.class));
			model.addAttribute("itineraryForm", itinerary);
		} else {
			model.addAttribute("placeSize", 0);
			model.addAttribute("itineraryForm", new Itinerary());
		}
		return "itinerary/createForm";
	}

	@RequestMapping(method = RequestMethod.GET, value = "appendPlace")
	protected String appendPlaceField(@RequestParam Integer fieldId,
			ModelMap model) {
		model.addAttribute("placeNumber", fieldId);
		model.addAttribute("itineraryPlace", new Itinerary());
		return "itinerary/addPlace";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "removePlace")
	protected void removePlaceField(@RequestParam Integer fieldId) {
		removedIndexes.add(fieldId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "add")
	protected ModelAndView create(Model model,
			@Valid @ModelAttribute("itineraryForm") Itinerary itineraryForm,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();

		clearRemovedPlaces(itineraryForm);
		if (result.hasErrors()) {
			modelAndView.setViewName("itinerary/createForm");

			if (itineraryForm != null && itineraryForm.getPlaces() != null) {
				modelAndView.addObject("placeSize", itineraryForm.getPlaces()
						.size());
			}
		} else {
			itineraryService.save(itineraryForm);
			modelAndView.setViewName("redirect:/");
			updateCurrentItinerary(modelAndView);
		}

		return modelAndView;
	}

	private void clearRemovedPlaces(Itinerary itineraryForm) {
		List<Place> originalPlaces = itineraryForm.getPlaces();
		
		if (originalPlaces == null || originalPlaces.size() == 0) {
			return;
		}
		
		AutoPopulatingList<Place> cleanPlaces = new AutoPopulatingList<Place>(Place.class);
		
		for (int i = 0; i < originalPlaces.size(); i++) {
			if (! removedIndexes.contains(i)) {
				cleanPlaces.add(originalPlaces.get(i));
			} 
		}
		removedIndexes.clear();
		itineraryForm.setPlaces(cleanPlaces);
	}

	private Itinerary updateCurrentItinerary(ModelAndView modelAndView) {
		Itinerary currentItinerary = itineraryService.getNext();
		
		if (currentItinerary != null) {
			modelAndView.addObject("currentItinerary", currentItinerary);
		}

		return currentItinerary;
	}

}
