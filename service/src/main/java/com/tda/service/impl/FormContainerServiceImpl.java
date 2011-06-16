package com.tda.service.impl;

import java.util.List;

import com.tda.model.utils.FormContainer;
import com.tda.model.utils.FormType;
import com.tda.persistence.dao.DentistFormDAO;
import com.tda.persistence.dao.FormContainerDAO;
import com.tda.persistence.dao.NurseFormDAO;
import com.tda.persistence.dao.PatientInTrainDAO;
import com.tda.persistence.dao.PediatricianFormDAO;
import com.tda.persistence.dao.SocialWorkerFormDAO;
import com.tda.persistence.paginator.Paginator;
import com.tda.service.api.FormContainerService;

public class FormContainerServiceImpl implements FormContainerService {

	private FormContainerDAO formContainerDAO;
	private SocialWorkerFormDAO socialWorkerFormDAO;
	private DentistFormDAO dentistFormDAO;
	private NurseFormDAO nurseFormDAO;
	private PediatricianFormDAO pediatricianFormDAO;
	private PatientInTrainDAO patientInTrainDAO;

	public void setSocialWorkerFormDAO(SocialWorkerFormDAO socialWorkerFormDAO) {
		this.socialWorkerFormDAO = socialWorkerFormDAO;
	}

	public void setDentistFormDAO(DentistFormDAO dentistFormDAO) {
		this.dentistFormDAO = dentistFormDAO;
	}

	public void setNurseFormDAO(NurseFormDAO nurseFormDAO) {
		this.nurseFormDAO = nurseFormDAO;
	}

	public void setPediatricianFormDAO(PediatricianFormDAO pediatricianFormDAO) {
		this.pediatricianFormDAO = pediatricianFormDAO;
	}

	public void setFormContainerDAO(FormContainerDAO formContainerDAO) {
		this.formContainerDAO = formContainerDAO;
	}

	public void setPatientInTrainDAO(PatientInTrainDAO patientInTrainDAO) {
		this.patientInTrainDAO = patientInTrainDAO;
	}

	public List<FormContainer> findByExamplePaged(FormContainer example,
			Paginator paginator) {
		return formContainerDAO.findByExamplePaged(example, paginator);
	}

	public void delete(FormType formType, Long id) {
		switch (formType) {
		case dentist:
			dentistFormDAO.deleteById(id);
			break;
		case nurse:
			nurseFormDAO.deleteById(id);
			break;
		case pediatrician:
			pediatricianFormDAO.deleteById(id);
			break;
		case socialworker:
			socialWorkerFormDAO.deleteById(id);
			break;
		}
	}
}
