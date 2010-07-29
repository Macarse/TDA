package com.tda.model.patient;

import java.util.LinkedHashMap;

public enum Sex {
	male, female;

	private static final String FEMALE = "Femenino";
	private static final String MALE = "Masculino";
	static LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
	static LinkedHashMap<String, String> keys = new LinkedHashMap<String, String>();

	static {
		values.put(male.toString(), MALE);
		values.put(female.toString(), FEMALE);

		keys.put(MALE, male.toString());
		keys.put(FEMALE, female.toString());
	}

	public static LinkedHashMap<String, String> getMap() {
		return values;
	}

	public static String getKey(String sex) {
		return keys.get(sex);
	}

	public static String getName(Sex sex) {
		return values.get(sex.toString());
	}
}
