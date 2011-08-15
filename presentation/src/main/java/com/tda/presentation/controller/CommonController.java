package com.tda.presentation.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.applicationuser.Authority;
import com.tda.model.itinerary.Itinerary;
import com.tda.persistence.dao.ItineraryDAO;
import com.tda.service.api.ApplicationUserService;

@SessionAttributes({ "user" })
public class CommonController {
	protected ApplicationUserService applicationUserService;
	protected ItineraryDAO itineraryDAO;

	@Autowired
	public void setItineraryDAO(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}
	
	@Autowired
	public void setApplicationUserService(
			ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}
	
	@ModelAttribute("userList")
	public List<ApplicationUser> getUserList() {
		List<ApplicationUser> users = applicationUserService.findAll();
		
		//actual user
		Object aux = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = ((UserDetails) aux);

		// set admin authority
		Authority adminAuth = new Authority();
		adminAuth.setAuthority("ROLE_ADMIN");

		// ROLE_USER
		Authority userAuth = new Authority();
		userAuth.setAuthority("ROLE_USER");

		Iterator<ApplicationUser> iter = users.iterator();
		ApplicationUser appUser;

		while (iter.hasNext()) {
			appUser = iter.next();
			Collection<Authority> auths = appUser.getMyAuthorities();
			
			if ((auths.size() == 2 && auths.contains(adminAuth)
					&& auths.contains(userAuth)) || appUser.getUsername().equals(user.getUsername())) {
				iter.remove();
			}
		}

		return users;
	}
	
	@ModelAttribute("currentItinerary")
	public Itinerary getCurrentItinerary() {
		Itinerary itinerary = itineraryDAO.findNextItinerary();

		if (itinerary == null) {
			return new Itinerary();
		} else {
			return itinerary;
		}
	}
}
