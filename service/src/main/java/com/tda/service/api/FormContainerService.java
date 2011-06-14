package com.tda.service.api;

import java.util.List;

import com.tda.model.utils.FormContainer;
import com.tda.persistence.paginator.Paginator;

public interface FormContainerService {
	List<FormContainer> findByExamplePaged(FormContainer example,
			Paginator paginator);
}
