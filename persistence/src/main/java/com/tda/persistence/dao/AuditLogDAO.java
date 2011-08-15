package com.tda.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import com.tda.model.audit.AuditLog;
import com.tda.persistence.paginator.Paginator;

public class AuditLogDAO extends GenericDAOImpl<AuditLog> {

	@Override
	protected Class<AuditLog> getDomainClass() {
		return AuditLog.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuditLog> findByExamplePaged(AuditLog al, Paginator paginator) {
		String DATE_FORMAT = "yyyy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		String query = "from AuditLog";
		String union = " where ";

		if (al.getTimestampFrom() != null) {
			query += (union + "DATE(timestamp) >= '"
					+ sdf.format(al.getTimestampFrom()) + "' ");
			union = " and ";
		}

		if (al.getTimestampTo() != null) {
			query += (union + "DATE(timestamp) <= '"
					+ sdf.format(al.getTimestampTo()) + "' ");
			union = " and ";
		}

		if (al.getControllerUsed() != null && !al.getControllerUsed().isEmpty()) {
			query += (union + " controllerUsed like '%"
					+ al.getControllerUsed() + "%' ");
			union = " and ";
		}

		if (al.getUser() != null && !al.getUser().isEmpty()) {
			query += (union + " user like '%" + al.getUser() + "%' ");
			union = " and ";
		}

		if (al.getFormId() != null) {
			query += (union + " formId=" + al.getFormId());
			union = " and ";
		}

		List<Object> asd = getHibernateTemplate().find(
				"select count(*) " + query);
		Long count = (Long) asd.get(0);

		paginator.setTotalResultsCount(count.intValue());

		if (paginator.getOrderField() != null
				&& paginator.getOrderField() != "")
			query += " ORDER BY " + paginator.getOrderField() + " "
					+ (paginator.getOrderAscending() ? "ASC" : "DESC");

		query += " LIMIT "
				+ (paginator.getResultsPerPage() * (paginator.getPageIndex() - 1))
				+ "," + paginator.getResultsPerPage();

		List<AuditLog> list = getHibernateTemplate().find(query);

		if (list == null || list.size() <= 0)
			return null;

		return list;
	}
}
