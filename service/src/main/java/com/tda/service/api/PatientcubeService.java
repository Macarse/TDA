package com.tda.service.api;

import java.util.List;

import com.tda.model.itinerary.PatientCube;
import com.tda.model.report.NbiForDestinationReport;

public interface PatientcubeService {

	void save(PatientCube authority);

	void delete(PatientCube authority);

	void update(PatientCube authority);

	PatientCube findById(Long id);

	List<PatientCube> findAll();

	List<PatientCube> findByExample(PatientCube example);

	List<NbiForDestinationReport> findNbiForDestination();
}
