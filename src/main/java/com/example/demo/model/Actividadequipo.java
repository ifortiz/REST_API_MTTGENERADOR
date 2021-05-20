package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Actividadequipo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long numactividad;
	private String descripcion;
	
	public Actividadequipo() {
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	private Equipo equipo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actividad", referencedColumnName = "id")
	private Actividad actividad;
	
	
	public long getId() {
		return id;
	}

	public long getNumactividad() {
		return numactividad;
	}

	public void setNumactividad(long numactividad) {
		this.numactividad = numactividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	private static final long serialVersionUID = 1L;

}
