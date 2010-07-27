package com.tda.model.applicationuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class ApplicationUser extends ApplicationUserGWT implements UserDetails {
	private static final long serialVersionUID = 1L;

	public ApplicationUser() {
		super();
	}

	public ApplicationUser(String username, String password,
			Collection<Authority> authorities, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {

		super(username, password, authorities, isAccountNonExpired,
				isAccountNonLocked, isCredentialsNonExpired, isEnabled);
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Authority authority : getMyAuthorities()) {
			list.add(new GrantedAuthorityImpl(authority.getAuthority()));
		}
		return list;
	}
}
