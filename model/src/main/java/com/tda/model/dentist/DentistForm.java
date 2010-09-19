package com.tda.model.dentist;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.tda.model.patient.Patient;

@Entity
public class DentistForm {
	private Long id;

	private Patient patient;

	private Date fillingDate;

	private String comment;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@Transient
	public boolean isNew() {
		return id == null;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
