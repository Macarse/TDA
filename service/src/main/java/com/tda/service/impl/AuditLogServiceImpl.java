package com.tda.service.impl;

import java.util.List;

import com.tda.model.audit.AuditLog;
import com.tda.persistence.dao.AuditLogDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.AuditLogService;

public class AuditLogServiceImpl implements AuditLogService {

    private AuditLogDAO auditLogDAO;

    public void setAuditLogDAO(AuditLogDAO auditLogDAO) {
        this.auditLogDAO = auditLogDAO;
    }

    public void save(AuditLog auditLog) {
        auditLogDAO.save(auditLog);
    }

    public List<AuditLog> findByExamplePaged(AuditLog example,
            Paginator paginator) {
        return auditLogDAO.findByExamplePaged(example, paginator);
    }

}
