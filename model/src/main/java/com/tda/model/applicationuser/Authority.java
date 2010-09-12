package com.tda.model.applicationuser;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority implements Comparable<Authority>, Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Authority o) {
		if (this.authority.equalsIgnoreCase(o.authority))
			return 0;

		return 1;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Authority))
			return false;

		if (this.authority.equalsIgnoreCase(((Authority) obj).authority))
			return true;

		return false;
	}

}