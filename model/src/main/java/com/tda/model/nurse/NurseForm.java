package com.tda.model.nurse;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.sun.tools.javac.util.List;
import com.tda.model.patient.Patient;

@Entity
public class NurseForm {
	private Long id;

	private Patient patient;

	private Date fillingDate;

	// Vital Checks
	/* TODO: Ask if this size is footSize. */
	private Double size;

	private Double weight;

	private Double headCircumference;

	private Double percentile;

	private Double TAmin;

	private Double TAmax;

	// TODO VACCINES

	private List<NurseAction> nurseActions;

	// TODO CURATION

	private String observations;

	// TODO MALFORMATIONS TABLE

	// INTERNMENT
	private boolean interconsultation;
	private boolean internment;

	private String treatment;

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

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeadCircumference() {
		return headCircumference;
	}

	public void setHeadCircumference(Double headCircumference) {
		this.headCircumference = headCircumference;
	}

	public Double getPercentile() {
		return percentile;
	}

	public void setPercentile(Double percentile) {
		this.percentile = percentile;
	}

	public Double getTAmin() {
		return TAmin;
	}

	public void setTAmin(Double tAmin) {
		TAmin = tAmin;
	}

	public Double getTAmax() {
		return TAmax;
	}

	public void setTAmax(Double tAmax) {
		TAmax = tAmax;
	}

	@Enumerated
	public List<NurseAction> getNurseActions() {
		return nurseActions;
	}

	public void setNurseActions(List<NurseAction> nurseActions) {
		this.nurseActions = nurseActions;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public boolean isInterconsultation() {
		return interconsultation;
	}

	public void setInterconsultation(boolean interconsultation) {
		this.interconsultation = interconsultation;
	}

	public boolean isInternment() {
		return internment;
	}

	public void setInternment(boolean internment) {
		this.internment = internment;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

}
