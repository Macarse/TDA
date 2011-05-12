package com.tda.model.nurse;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vaxine implements Comparable<Vaxine>, Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Boolean isOptative;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	public Boolean isOptative() {
		return isOptative;
	}

	public void setOptative(Boolean isOptative) {
		this.isOptative = isOptative;
	}

	public int compareTo(Vaxine o) {
		if (this.name.equalsIgnoreCase(o.name)
				&& this.isOptative == o.isOptative)
			return 0;

		return 1;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vaxine))
			return false;

		Vaxine o = (Vaxine) obj;
		if (this.name.equalsIgnoreCase(o.name)
				&& this.isOptative == o.isOptative)
			return true;

		return false;
	}
}