package com.tda.model.pediatrician;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PediatricianDiagnosis implements
		Comparable<PediatricianDiagnosis>, Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;

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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(PediatricianDiagnosis o) {
		if (this.name.equalsIgnoreCase(o.name)
				&& this.description == o.description)
			return 0;

		return 1;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PediatricianDiagnosis))
			return false;

		PediatricianDiagnosis o = (PediatricianDiagnosis) obj;
		if (this.name.equalsIgnoreCase(o.name)
				&& this.description == o.description)
			return true;

		return false;
	}
}