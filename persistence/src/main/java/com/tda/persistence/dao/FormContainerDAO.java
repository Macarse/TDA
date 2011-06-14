package com.tda.persistence.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tda.model.utils.FormContainer;
import com.tda.model.utils.FormType;
import com.tda.persistence.paginator.Paginator;

public class FormContainerDAO extends GenericDAOImpl<FormContainer> {

	@Override
	protected Class<FormContainer> getDomainClass() {
		return FormContainer.class;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FormContainer> findByExamplePaged(final FormContainer example,
			final Paginator paginator) {
		List<FormContainer> list = (List<FormContainer>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<FormContainer> list = new ArrayList<FormContainer>();
						try {

							// Set the total Count
							String COUNT_SQL_QUERY = "(SELECT COUNT(*) FROM SocialWorkerForm WHERE patient_id="
									+ example.getPatient().getId()
									+ ") UNION (SELECT COUNT(*) FROM DentistForm WHERE patient_id="
									+ example.getPatient().getId()
									+ ") UNION (SELECT COUNT(*) FROM NurseForm WHERE patient_id="
									+ example.getPatient().getId()
									+ ") UNION (SELECT COUNT(*) FROM PediatricianForm WHERE patient_id="
									+ example.getPatient().getId() + ")";

							Statement stCount = session.connection()
									.createStatement();
							ResultSet rsCount = stCount
									.executeQuery(COUNT_SQL_QUERY);

							int count = 0;
							while (rsCount.next()) {
								count += rsCount.getInt(1);
							}

							paginator.setTotalResultsCount(count);

							// set data
							String SQL_QUERY = "(SELECT id, fillingDate, 'socialworker' as formType FROM SocialWorkerForm WHERE patient_id="
									+ example.getPatient().getId()
									+ ") UNION (SELECT id, fillingDate, 'dentist' as formType FROM DentistForm WHERE patient_id="
									+ example.getPatient().getId()
									+ ") UNION (SELECT id, fillingDate, 'nurse' as formType FROM NurseForm WHERE patient_id="
									+ example.getPatient().getId()
									+ ") UNION (SELECT id, fillingDate, 'pediatrician' as formType FROM PediatricianForm WHERE patient_id="
									+ example.getPatient().getId() + ")";

							if (paginator.getOrderField() != null
									&& paginator.getOrderField() != "")
								SQL_QUERY += " ORDER BY "
										+ paginator.getOrderField()
										+ " "
										+ (paginator.getOrderAscending() ? "ASC"
												: "DESC");

							SQL_QUERY += " LIMIT "
									+ (paginator.getResultsPerPage() * (paginator
											.getPageIndex() - 1)) + ","
									+ paginator.getResultsPerPage();

							Statement st = session.connection()
									.createStatement();
							ResultSet rs = st.executeQuery(SQL_QUERY);

							while (rs.next()) {
								FormType formType = FormType.valueOf(rs
										.getString(3));
								FormContainer afr = new FormContainer(rs
										.getLong(1), rs.getDate(2), formType,
										example.getPatient());
								list.add(afr);
							}
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
