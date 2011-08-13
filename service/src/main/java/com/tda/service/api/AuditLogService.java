package com.tda.service.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.tda.model.audit.AuditLog;
import com.tda.persistence.paginator.Paginator;

public interface AuditLogService {

	void save(AuditLog auditLog);

	List<AuditLog> findByExamplePaged(AuditLog example, Paginator paginator);

	Collection<AuditLog> findByExamplePaged(AuditLog al, Paginator paginator,
			Date from, Date to);
}
