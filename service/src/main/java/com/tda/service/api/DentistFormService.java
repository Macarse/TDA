package com.tda.service.api;

import java.util.List;

import com.tda.model.dentist.DentistForm;
import com.tda.persistence.paginator.Paginator;

public interface DentistFormService {
	void save(DentistForm dentistForm);

	void delete(DentistForm dentistForm);

	void update(DentistForm dentistForm);

	DentistForm findById(Long id);

	List<DentistForm> findAll();

	List<DentistForm> findAllPaged(Paginator paginator);

	List<DentistForm> findByExample(DentistForm dentistForm);
}
