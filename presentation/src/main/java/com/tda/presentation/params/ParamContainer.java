package com.tda.presentation.params;

import java.util.HashMap;
import java.util.Map;

public class ParamContainer {
	private Map<String, String> params;
	
	public ParamContainer() {
		this.params = new HashMap<String, String>();
	}
	
	public void setParam(String key, String value) {
		this.params.put(key, value);
	}
	
	public String getParam(String key){
		return this.params.get(key);
	}

	public String toString() {
		String ret = "";
		
		for (String e : this.params.keySet()) {
			ret += e + "=" + this.params.get(e) + "&";
		}
		
		return ret;
	}
}
