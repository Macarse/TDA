package com.tda.model.patientqueue;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.model.patient.Patient;

@Entity
public class PatientQueue implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Patient patient;
	private ApplicationUser user;
	private Date time;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@OneToOne
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@NotNull
	@OneToOne
	public ApplicationUser getUser() {
		return user;
	}
	
	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	

}
