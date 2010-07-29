package com.tda.model.applicationuser;

import java.util.LinkedHashMap;

public enum Authorities {
	ROLE_ADMIN, ROLE_DENTIST, ROLE_MEDIC, ROLE_PEDIATRICIAN, ROLE_NURSE, ROLE_SOCIAL_WORKER, ROLE_USER;

	private static final String ADMIN = "Administrador";
	private static final String DENTIST = "Dentista";
	private static final String MEDIC = "Medico";
	private static final String PEDIATRICIAN = "Pediatra";
	private static final String NURSE = "Enfermero";
	private static final String SOCIAL = "Trabajador Social";
	private static final String USER = "Usuario";

	static LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
	static LinkedHashMap<String, String> keys = new LinkedHashMap<String, String>();

	static {
		values.put(ROLE_ADMIN.toString(), ADMIN);
		values.put(ROLE_DENTIST.toString(), DENTIST);
		values.put(ROLE_MEDIC.toString(), MEDIC);
		values.put(ROLE_PEDIATRICIAN.toString(), PEDIATRICIAN);
		values.put(ROLE_NURSE.toString(), NURSE);
		values.put(ROLE_SOCIAL_WORKER.toString(), SOCIAL);
		values.put(ROLE_USER.toString(), USER);

		keys.put(ADMIN, ROLE_ADMIN.toString());
		keys.put(DENTIST, ROLE_DENTIST.toString());
		keys.put(MEDIC, ROLE_MEDIC.toString());
		keys.put(PEDIATRICIAN, ROLE_PEDIATRICIAN.toString());
		keys.put(NURSE, ROLE_NURSE.toString());
		keys.put(SOCIAL, ROLE_SOCIAL_WORKER.toString());
		keys.put(USER, ROLE_USER.toString());
	}

	public static LinkedHashMap<String, String> getMap() {
		return values;
	}

	public static String getKey(String auth) {
		return keys.get(auth);
	}

	public static String getName(String auth) {
		return values.get(auth);
	}
}
