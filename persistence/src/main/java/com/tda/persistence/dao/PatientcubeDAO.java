package com.tda.persistence.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tda.model.itinerary.PatientCube;
import com.tda.model.report.AgeForDestinationReport;
import com.tda.model.report.InterconsultPerYearReport;
import com.tda.model.report.NbiForDestinationReport;
import com.tda.model.report.PrevalentDiagnosticForDestinationReport;
import com.tda.model.report.ScholarityByDestinationReport;
import com.tda.model.utils.FormType;

public class PatientcubeDAO extends GenericDAOImpl<PatientCube> {
	@Override
	protected Class<PatientCube> getDomainClass() {
		return PatientCube.class;
	}

	@SuppressWarnings("unchecked")
	public List<PatientCube> findAllWithForm(FormType type) {

		List<PatientCube> list = getHibernateTemplate().find(
				"from PatientCube WHERE formType = " + type);

		if (list == null || list.size() <= 0)
			return null;

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<NbiForDestinationReport> findNbiForDestination() {

		List<NbiForDestinationReport> list = (List<NbiForDestinationReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<NbiForDestinationReport> list = new ArrayList<NbiForDestinationReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "select pc.place, nb.nbi, COUNT(*) from patientcube as pc inner join socialworkerform as swf on pc.formId=swf.id inner join NBI as nb on swf.id=nb.social_worker_id where pc.formType = "
									+ FormType.socialworker.ordinal()
									+ " group by pc.place, nb.nbi";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								NbiForDestinationReport afr = new NbiForDestinationReport(
										rs.getString(1), rs.getString(2), rs
												.getInt(3));
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
	public List<InterconsultPerYearReport> findInterconsultPerYear() {

		List<InterconsultPerYearReport> list = (List<InterconsultPerYearReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<InterconsultPerYearReport> list = new ArrayList<InterconsultPerYearReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "select YEAR(pc.insertedDate), COUNT(*) from patientcube as pc inner join pediatricianform as pf on pc.formId=pf.id where pf.interconsultation=1 AND pc.formType = "
									+ FormType.pediatrician.ordinal()
									+ " group by YEAR(pc.insertedDate)";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								InterconsultPerYearReport afr = new InterconsultPerYearReport(
										rs.getString(1), rs.getInt(2));
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
	public List<AgeForDestinationReport> findAgeForDestination() {

		List<AgeForDestinationReport> list = (List<AgeForDestinationReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<AgeForDestinationReport> list = new ArrayList<AgeForDestinationReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "SELECT place, DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(birthdate, '%Y') - (DATE_FORMAT(NOW(), '00-%m-%d') < DATE_FORMAT(birthdate, '00-%m-%d')) AS age, COUNT(*) from Patient as pat inner join (select Distinct patientId,place from patientcube) as pc on pat.id=pc.patientId GROUP BY (DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(birthdate, '%Y') - (DATE_FORMAT(NOW(), '00-%m-%d') < DATE_FORMAT(birthdate, '00-%m-%d'))), place";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								AgeForDestinationReport afr = new AgeForDestinationReport(
										rs.getString(1), rs.getString(2), rs
												.getInt(3));
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

	public Collection<PrevalentDiagnosticForDestinationReport> findPrevalentDiagnosticForDestination() {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<PrevalentDiagnosticForDestinationReport> list = (List<PrevalentDiagnosticForDestinationReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<PrevalentDiagnosticForDestinationReport> list = new ArrayList<PrevalentDiagnosticForDestinationReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "select pc.place, pf.diagnosis, COUNT(*) from patientcube as pc inner join PediatricianForm as pf on pc.formId=pf.id where pc.formType = "
									+ FormType.pediatrician.ordinal()
									+ " group by pc.place, pf.diagnosis";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								PrevalentDiagnosticForDestinationReport afr = new PrevalentDiagnosticForDestinationReport(
										rs.getString(1), rs.getString(2), rs
												.getInt(3));
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

	public Collection<ScholarityByDestinationReport> findScholarityByDestination() {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<ScholarityByDestinationReport> list = (List<ScholarityByDestinationReport>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<ScholarityByDestinationReport> list = new ArrayList<ScholarityByDestinationReport>();
						try {
							// Transaction tx = session.beginTransaction();
							String SQL_QUERY = "select pc.place, swf.scholarity, COUNT(*) from patientcube as pc inner join SocialWorkerForm as swf on pc.formId=swf.id where pc.formType = "
									+ FormType.socialworker.ordinal()
									+ " group by pc.place, swf.scholarity";
							// Query query = session.createQuery(SQL_QUERY);

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								ScholarityByDestinationReport afr = new ScholarityByDestinationReport(
										rs.getString(1), rs.getString(2), rs
												.getInt(3));
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
