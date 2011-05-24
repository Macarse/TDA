package com.tda.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;

public class PatientDAO extends GenericDAOImpl<Patient> {

	@Override
	protected Class<Patient> getDomainClass() {
		return Patient.class;
	}

	public List<Patient> findByFirstNameContaining(String name) {
		Patient exampleObject = PatientBuilder.createPatient()
				.withFirstName(name).build();
		return this.findByExample(exampleObject);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> findPatientsAttendedByDate(Date from, Date to) {
		String DATE_FORMAT = "yyyy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		List<Patient> list = getHibernateTemplate()
				.find("from Patient WHERE id in (select patient.id from SocialWorkerForm where fillingDate > '"
						+ sdf.format(from)
						+ "' and fillingDate < '"
						+ sdf.format(to)
						+ "') or id in ("
						+ "select patient.id from PediatricianForm where fillingDate > '"
						+ sdf.format(from)
						+ "' and fillingDate < '"
						+ sdf.format(to)
						+ "') or id in ("
						+ "select patient.id from NurseForm where fillingDate > '"
						+ sdf.format(from)
						+ "' and fillingDate < '"
						+ sdf.format(to)
						+ "') or id in ("
						+ "select patient.id from DentistForm where fillingDate > '"
						+ sdf.format(from)
						+ "' and fillingDate < '"
						+ sdf.format(to) + "')");

		if (list == null || list.size() <= 0)
			return null;

		return list;
	}
}
