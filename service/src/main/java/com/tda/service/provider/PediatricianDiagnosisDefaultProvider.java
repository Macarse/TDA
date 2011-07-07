package com.tda.service.provider;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.tda.model.pediatrician.PediatricianDiagnosis;
import com.tda.service.api.PediatricianDiagnosisService;

public class PediatricianDiagnosisDefaultProvider implements
		PediatricianDiagnosisProvider {
	private Properties propertiesHolder;

	private PediatricianDiagnosisService pediatricianDiagnosisService;

	public void init() {
		loadDefaultPediatricianDiagnosis(propertiesHolder);
	}

	public List<String> getAllDiagnosis() {
		List<String> pediatricianDiagnosisList = new ArrayList<String>();

		for (PediatricianDiagnosis diagnosis : pediatricianDiagnosisService
				.findAll()) {
			pediatricianDiagnosisList.add(diagnosis.getName());
		}

		return pediatricianDiagnosisList;
	}

	private void loadDefaultPediatricianDiagnosis(final Properties properties) {
		for (Enumeration<Object> en = properties.keys(); en.hasMoreElements();) {
			String key = (String) en.nextElement();

			String[] props = key.split(",");

			Long id = Long.parseLong(props[0]);
			String name = props[1].replace('_', ' ');
			String description = props[2].replace('_', ' ');
			description = description.replace('+', ':');

			List<PediatricianDiagnosis> pediatricianDiagnosiss = null;
			PediatricianDiagnosis example = new PediatricianDiagnosis();
			example.setId(id);
			example.setName(name);
			example.setDescription(description);
			pediatricianDiagnosiss = pediatricianDiagnosisService
					.findByExample(example);
			if (pediatricianDiagnosiss.size() == 0) {
				PediatricianDiagnosis pediatricianDiagnosis = new PediatricianDiagnosis();
				pediatricianDiagnosis.setId(id);
				pediatricianDiagnosis.setName(name);
				pediatricianDiagnosis.setDescription(description);
				pediatricianDiagnosisService.save(pediatricianDiagnosis);
			}
		}
	}

	public void setPropertiesHolder(final Properties propertiesHolder) {
		this.propertiesHolder = propertiesHolder;
	}

	public PediatricianDiagnosisService getPediatricianDiagnosisService() {
		return pediatricianDiagnosisService;
	}

	public void setPediatricianDiagnosisService(
			PediatricianDiagnosisService pediatricianDiagnosisService) {
		this.pediatricianDiagnosisService = pediatricianDiagnosisService;
	}
}
