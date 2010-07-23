package com.tda.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class ApplicationUserBuilder {
	private String password = "password";
	private String username = "username";
	private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	private boolean isAccountNonExpired = true;
	private boolean isAccountNonLocked = true;
	private boolean isCredentialsNonExpired = true;
	private boolean isEnabled = true;

	private ApplicationUserBuilder() {
	}

	public static ApplicationUserBuilder createApplicationUser() {
		return new ApplicationUserBuilder();
	}

	public ApplicationUser build() {
		return new ApplicationUser(username, password, authorities,
				isAccountNonExpired, isAccountNonLocked,
				isCredentialsNonExpired, isEnabled);
	}

	public ApplicationUserBuilder withUsername(String username) {
		this.username = username;
		return this;
	}

	public ApplicationUserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public ApplicationUserBuilder withAuthorities(
			Collection<GrantedAuthority> authorities) {
		this.authorities.addAll(authorities);
		return this;
	}

	public ApplicationUserBuilder withAuthority(GrantedAuthority authority) {
		this.authorities.add(authority);
		return this;
	}

	public ApplicationUserBuilder withAccountNonExpired() {
		this.isAccountNonExpired = true;
		return this;
	}

	public ApplicationUserBuilder withAccountExpired() {
		this.isAccountNonExpired = false;
		return this;
	}

	public ApplicationUserBuilder withAccountNonLocked() {
		this.isAccountNonLocked = true;
		return this;
	}

	public ApplicationUserBuilder withAccountLocked() {
		this.isAccountNonLocked = false;
		return this;
	}

	public ApplicationUserBuilder withCredentialsNonExpired() {
		this.isCredentialsNonExpired = true;
		return this;
	}

	public ApplicationUserBuilder withCredentialsExpired() {
		this.isCredentialsNonExpired = false;
		return this;
	}

	public ApplicationUserBuilder enabled() {
		this.isEnabled = true;
		return this;
	}

	public ApplicationUserBuilder disabled() {
		this.isEnabled = false;
		return this;
	}
}
