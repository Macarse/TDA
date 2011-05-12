package com.tda.model.itinerary;

import java.util.Date;

import org.hibernate.validator.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.AutoPopulatingList;

public class ItineraryForm {
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date beginningDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	@Size(max = 200)
	private String description;

	private AutoPopulatingList<Place> places;

	@Size(max = 200)
	private String additionalInfo;

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

	public AutoPopulatingList<Place> getPlaces() {
		return places;
	}

	public void setPlaces(AutoPopulatingList<Place> places) {
		this.places = places;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
