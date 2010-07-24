package com.tda.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class ApplicationUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String password;
	private String username;
	private Collection<Authority> myAuthorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;

	public ApplicationUser() {
		super();
	}

	public ApplicationUser(String username, String password,
			Collection<Authority> authorities, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		this.username = username;
		this.password = password;
		this.myAuthorities = authorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	@Basic
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Authority authority : myAuthorities) {
			list.add(new GrantedAuthorityImpl(authority.getAuthority()));
		}
		return list;
	}

	@ManyToMany(targetEntity = Authority.class, cascade = { CascadeType.ALL })
	@ForeignKey(name = "ID_USER", inverseName = "ID_AUTH")
	public Collection<Authority> getMyAuthorities() {
		return myAuthorities;
	}

	public void setMyAuthorities(Collection<Authority> myAuthorities) {
		this.myAuthorities = myAuthorities;
	}

	@Basic
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	@Basic
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	@Basic
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	@Basic
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

}
