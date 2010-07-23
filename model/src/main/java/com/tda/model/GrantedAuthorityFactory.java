package com.tda.model;

public class GrantedAuthorityFactory {
	private GrantedAuthority() {
	}

	public static GrantedAuthority createDentistAuthority() {
		GrantedAuthority result = new GrantedAuthority();
		result.setAuthority("ROLE_DENTIST");
		return result;
	}
}
