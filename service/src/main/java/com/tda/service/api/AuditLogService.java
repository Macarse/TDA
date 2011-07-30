package com.tda.service.api;

import java.util.List;

import com.tda.model.audit.AuditLog;
import com.tda.persistence.paginator.Paginator;

public interface AuditLogService {

    void save(AuditLog auditLog);

    List<AuditLog> findByExamplePaged(AuditLog example, Paginator paginator);
}
