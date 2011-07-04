package com.tda.persistence.dao;

import com.tda.model.pediatrician.PediatricianDiagnosis;

public class PediatricianDiagnosisDAO extends
		GenericDAOImpl<PediatricianDiagnosis> {
	@Override
	protected Class<PediatricianDiagnosis> getDomainClass() {
		return PediatricianDiagnosis.class;
	}
}
