package com.tda.model.report;

import java.util.Date;
import java.util.List;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.itinerary.Place;

public class ItineraryForReport {

	private Long id;
	private Date beginningDate;
	private Date endDate;
	private String description;
	private List<Place> places;
	private List<ApplicationUser> personnel;
	private String additionalInfo;
	private Integer attendedPatients;
	private String placesStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<ApplicationUser> getPersonnel() {
		return personnel;
	}

	public void setPersonnel(List<ApplicationUser> personnel) {
		this.personnel = personnel;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Integer getAttendedPatients() {
		return attendedPatients;
	}

	public void setAttendedPatients(Integer attendedPatients) {
		this.attendedPatients = attendedPatients;
	}

	public String getPlacesStr() {
		return placesStr;
	}

	public void setPlacesStr(String placesStr) {
		this.placesStr = placesStr;
	}

	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", beginningDate=" + beginningDate
				+ ", endDate=" + endDate + ", description=" + description
				+ ", places=" + places + ", personnel=" + personnel
				+ ", additionalInfo=" + additionalInfo + "]";
	}

}
