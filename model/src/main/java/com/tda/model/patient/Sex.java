package com.tda.model.patient;

import java.util.LinkedHashMap;

public enum Sex {
	male, female;

	public static LinkedHashMap<String, String> getMap() {
		LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>();

		ret.put(male.toString(), "Masculino");
		ret.put(female.toString(), "Femenino");

		return ret;
	}
}
