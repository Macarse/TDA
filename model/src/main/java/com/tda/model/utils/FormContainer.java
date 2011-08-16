package com.tda.model.utils;

import java.util.Date;

import com.tda.model.patient.Patient;

public class FormContainer {
	private Long id;

	private Patient patient;

	private Date fillingDate;

	private FormType formType;

	public FormContainer(Long id, Date fillingDate, FormType formType,
			Patient patient) {
		this.id = id;
		this.patient = patient;
		this.fillingDate = fillingDate;
		this.formType = formType;
	}

	public FormContainer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public FormType getFormType() {
		return formType;
	}

	public void setFormType(FormType formType) {
		this.formType = formType;
	}

}
