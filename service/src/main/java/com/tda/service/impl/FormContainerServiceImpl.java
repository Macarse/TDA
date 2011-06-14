package com.tda.service.impl;

import java.util.List;

import com.tda.model.utils.FormContainer;
import com.tda.persistence.dao.FormContainerDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.FormContainerService;

public class FormContainerServiceImpl implements FormContainerService {

	private FormContainerDAO formContainerDAO;

	public void setFormContainerDAO(FormContainerDAO formContainerDAO) {
		this.formContainerDAO = formContainerDAO;
	}

	public List<FormContainer> findByExamplePaged(FormContainer example,
			Paginator paginator) {
		return formContainerDAO.findByExamplePaged(example, paginator);
	}
}
