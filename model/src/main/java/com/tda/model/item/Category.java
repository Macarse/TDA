package com.tda.model.item;

import java.util.LinkedHashMap;

public enum Category {
	misc, medical;

	public static LinkedHashMap <String, String> getMap() {
		LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>();

		ret.put(medical.toString(), "Medicinal");
		ret.put(misc.toString(), "Miscelánea");

		return ret;
	}
}