package com.tda.model.patient;

import java.io.Serializable;
import java.util.Date;

public class PatientBuilder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dni;

	private String name;

	private Sex sex;

	private Date birthdate;

	private String fatherName;

	private String motherName;

	private PatientBuilder() {
	}

	public static PatientBuilder createPatient() {
		return new PatientBuilder();
	}

	public Patient build() {
		return new Patient(dni, name, sex, birthdate, fatherName, motherName);
	}

	public PatientBuilder withDni(String dni) {
		this.dni = dni;
		return this;
	}

	public PatientBuilder withName(String name) {
		this.name = name;
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

	public PatientBuilder withFatherName(String fatherName) {
		this.fatherName = fatherName;
		return this;
	}

	public PatientBuilder withMotherName(String motherName) {
		this.motherName = motherName;
		return this;
	}
}
