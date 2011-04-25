package com.tda.model.itinerary;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.tda.model.applicationuser.ApplicationUser;

@Entity
public class Itinerary {
	private Long id;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date beginningDate;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	@Size(max = 200)
	private String description;

	private List<Place> places;

	private List<ApplicationUser> personnel;

	@Size(max = 200)
	private String additionalInfo;

	public Itinerary(Date beginningDate, Date endDate, String description,
			List<Place> places, List<ApplicationUser> personnel,
			String additionalInfo) {
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.description = description;
		this.places = places;
		this.personnel = personnel;
		this.additionalInfo = additionalInfo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@OneToMany
	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@OneToMany
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

}
