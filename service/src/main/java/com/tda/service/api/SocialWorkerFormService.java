package com.tda.service.api;

import java.util.List;

import com.tda.model.socialworker.SocialWorkerForm;
import com.tda.persistence.paginator.Paginator;

public interface SocialWorkerFormService {
	void save(SocialWorkerForm socialWorkerForm);

	void delete(SocialWorkerForm socialWorkerForm);

	void update(SocialWorkerForm socialWorkerForm);

	SocialWorkerForm findById(Long id);

	List<SocialWorkerForm> findAll();

	List<SocialWorkerForm> findAllPaged(Paginator paginator);

	List<SocialWorkerForm> findByExample(SocialWorkerForm socialWorkerForm);

	SocialWorkerForm findLastByPatientId(Long id);

}
