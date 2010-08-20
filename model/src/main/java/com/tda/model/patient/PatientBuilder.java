package com.tda.model.patient;

import java.io.Serializable;
import java.util.Date;

public class PatientBuilder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dni;
	private String firstName;
	private String lastName;
	private Sex sex;
	private Date birthdate;

	private PatientBuilder() {
	}

	public static PatientBuilder createPatient() {
		return new PatientBuilder();
	}

	public Patient build() {
		return new Patient(dni, firstName, lastName, sex, birthdate);
	}

	public PatientBuilder withDni(String dni) {
		this.dni = dni;
		return this;
	}

	public PatientBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public PatientBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public PatientBuilder withSex(Sex sex) {
		this.sex = sex;
		return this;
	}

	public PatientBuilder withBirthdate(Date birthdate) {
		this.birthdate = birthdate;
		return this;
	}

}
