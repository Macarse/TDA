package com.tda.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Prueba {
	private int id;

	private String nombre;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Basic
	public String getNombre() {
		return nombre;
	}

}
