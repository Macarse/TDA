package com.tda.persistence.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tda.model.itinerary.PatientCube;
import com.tda.model.report.InterconsultPerYearReport;
import com.tda.model.report.NbiForDestinationReport;
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
							String SQL_QUERY = "select pc.place, nb.nbi, COUNT(*) from patientcube as pc inner join socialworkerform as swf on pc.formId=swf.id inner join NBI as nb on swf.id=nb.social_worker_id group by pc.place, nb.nbi";
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
							String SQL_QUERY = "select YEAR(pc.insertedDate), COUNT(*) from patientcube as pc inner join pediatricianform as pf on pc.formId=pf.id where pf.interconsultation=1 group by YEAR(pc.insertedDate)";
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
}
