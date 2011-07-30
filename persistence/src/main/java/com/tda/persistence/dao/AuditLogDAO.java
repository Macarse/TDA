package com.tda.persistence.dao;

import com.tda.model.audit.AuditLog;

public class AuditLogDAO extends GenericDAOImpl<AuditLog> {

    @Override
    protected Class<AuditLog> getDomainClass() {
        return AuditLog.class;
    }

}
