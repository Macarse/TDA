package com.tda.model.item;

import java.util.LinkedHashMap;

public enum Category {
	misc, medical;

	private static final String MISC = "Miscelánea";
	private static final String MEDICAL = "Medicinal";
	static LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
	static LinkedHashMap<String, String> keys = new LinkedHashMap<String, String>();

	static {
		values.put(medical.toString(), MEDICAL);
		values.put(misc.toString(), MISC);

		keys.put(MISC, misc.toString());
		keys.put(MEDICAL, medical.toString());
	}

	public static LinkedHashMap<String, String> getMap() {
		return values;
	}

	public static String getKey(String category) {
		return keys.get(category);
	}

	public static String getName(Category category) {
		return values.get(category.toString());
	}
}