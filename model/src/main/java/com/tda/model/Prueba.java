package com.tda.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Prueba implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public String toString() {
		return "Prueba [id=" + id + ", nombre=" + nombre + "]";
	}
}
