package com.tda.model.item;

import java.util.LinkedHashMap;

public enum MeasureUnit {
	mts, lts, unit;

	public static LinkedHashMap<String, String> getMap() {
		LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>();

		ret.put(mts.toString(), "Metros");
		ret.put(lts.toString(), "Litros");
		ret.put(unit.toString(), "Unidades");

		return ret;
	}
}