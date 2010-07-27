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

	public int compareTo(Authority o) {
		if (this.authority.equalsIgnoreCase(o.authority))
			return 0;

		return 1;
	}
}
