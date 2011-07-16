package com.tda.persistence.dao;

import java.util.List;

import com.tda.model.socialworker.SocialWorkerForm;

public class SocialWorkerFormDAO extends GenericDAOImpl<SocialWorkerForm> {

	@Override
	protected Class<SocialWorkerForm> getDomainClass() {
		return SocialWorkerForm.class;
	}

	public SocialWorkerForm findLastByPatientId(Long id) {
		@SuppressWarnings("unchecked")
		List<SocialWorkerForm> list = getHibernateTemplate().find(
				"from SocialWorkerForm as swf where swf.patient.id=" + id
						+ " ORDER BY swf.fillingDate DESC");

		if (list == null || list.size() == 0)
			return null;

		return list.get(0);
	}

}
