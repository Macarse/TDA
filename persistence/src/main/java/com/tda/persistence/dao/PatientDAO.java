package com.tda.persistence.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;
import com.tda.model.report.AgeForReport;

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

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Patient> findPatientsBetweenAges(Integer from, Integer to) {

		Date dateFrom = new Date();
		dateFrom.setYear(dateFrom.getYear() - to);

		Date dateTo = new Date();
		dateTo.setYear(dateTo.getYear() - from);

		DetachedCriteria c = DetachedCriteria.forClass(persistentClass);
		c.add(Expression.between("birthdate", dateFrom, dateTo));

		List<Patient> list = getHibernateTemplate().findByCriteria(c);

		if (list == null || list.size() <= 0)
			return null;

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<AgeForReport> findGroupedAge() {

		Collection<AgeForReport> list = (Collection<AgeForReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Collection<AgeForReport> list = new ArrayList<AgeForReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "SELECT DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(birthdate, '%Y') - (DATE_FORMAT(NOW(), '00-%m-%d') < DATE_FORMAT(birthdate, '00-%m-%d')) AS age, COUNT(*) from Patient GROUP BY birthdate;";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								AgeForReport afr = new AgeForReport(rs
										.getInt(1), rs.getInt(2));
								list.add(afr);
							}

							// tx.commit();
							// session.flush();
							// releaseSession(session);
						} catch (Exception ex) {
						}
						return list;
					}
				});

		if (list == null || list.size() <= 0)
			return null;

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<AgeForReport> findGroupedAgeForDestination() {

		Collection<AgeForReport> list = (Collection<AgeForReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Collection<AgeForReport> list = new ArrayList<AgeForReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "SELECT DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(birthdate, '%Y') - (DATE_FORMAT(NOW(), '00-%m-%d') < DATE_FORMAT(birthdate, '00-%m-%d')) AS age, COUNT(*) from Patient GROUP BY birthdate;";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								AgeForReport afr = new AgeForReport(rs
										.getInt(1), rs.getInt(2));
								list.add(afr);
							}

							// tx.commit();
							// session.flush();
							// releaseSession(session);
						} catch (Exception ex) {
						}
						return list;
					}
				});

		if (list == null || list.size() <= 0)
			return null;

		return list;
	}
}
