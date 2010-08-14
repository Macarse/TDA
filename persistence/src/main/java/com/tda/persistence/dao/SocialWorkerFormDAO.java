package com.tda.persistence.dao;

import com.tda.model.socialworker.SocialWorkerForm;

public class SocialWorkerFormDAO extends GenericDAOImpl<SocialWorkerForm> {

	@Override
	protected Class<SocialWorkerForm> getDomainClass() {
		return SocialWorkerForm.class;
	}

}
