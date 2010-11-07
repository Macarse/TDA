package com.tda.service.provider;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.tda.model.nurse.Vaxine;
import com.tda.service.api.VaxineService;

public class VaxineDefaultProvider implements VaxineProvider {
	private Properties propertiesHolder;

	private VaxineService vaxineService;

	public void init() {
		loadDefaultVaxines(propertiesHolder);
	}

	public List<String> getAllVaxines() {
		List<String> vaxineList = new ArrayList<String>();

		for (Vaxine vaxine : vaxineService.findAll()) {
			vaxineList.add(vaxine.getName());
		}

		return vaxineList;
	}

	private void loadDefaultVaxines(final Properties properties) {
		for (Enumeration<Object> en = properties.keys(); en.hasMoreElements();) {
			String key = (String) en.nextElement();
			// String[] props = properties.getProperty(key).split(",");
			String[] props = key.split(",");
			String name = props[0].replace('_', ' ');
			String isOptative = props[1];

			List<Vaxine> vaxines = null;
			Vaxine example = new Vaxine();
			example.setName(name);
			vaxines = vaxineService.findByExample(example);
			if (vaxines.size() == 0) {
				Vaxine vaxine = new Vaxine();
				vaxine.setName(name);
				vaxine.setOptative(Boolean.parseBoolean(isOptative));
				vaxineService.save(vaxine);
			}
		}
	}

	public void setPropertiesHolder(final Properties propertiesHolder) {
		this.propertiesHolder = propertiesHolder;
	}

	public void setVaxineService(VaxineService vaxineService) {
		this.vaxineService = vaxineService;
	}

	public VaxineService getVaxineService() {
		return vaxineService;
	}
}
