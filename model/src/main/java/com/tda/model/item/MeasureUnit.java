package com.tda.model.item;

import java.util.LinkedHashMap;

public enum MeasureUnit {
	mts, lts, unit;

	private static final String UNITS = "Unidades";
	private static final String LITERS = "Litros";
	private static final String METERS = "Metros";

	static LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
	static LinkedHashMap<String, String> keys = new LinkedHashMap<String, String>();

	static {
		values.put(mts.toString(), METERS);
		values.put(lts.toString(), LITERS);
		values.put(unit.toString(), UNITS);

		keys.put(UNITS, unit.toString());
		keys.put(LITERS, lts.toString());
		keys.put(METERS, mts.toString());
	}

	public static LinkedHashMap<String, String> getMap() {
		return values;
	}

	public static String getKey(String measureUnit) {
		return keys.get(measureUnit);
	}

	public static String getName(MeasureUnit measureUnit) {
		return values.get(measureUnit.toString());
	}
}