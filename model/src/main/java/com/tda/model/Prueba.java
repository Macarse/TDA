package com.tda.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Prueba implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

	private String nombre;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	public Long getId() {
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
