package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipoprueba implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String marca;
	private String modelo;
	private String tipo;
	
	@OneToMany(mappedBy = "equipoprueba")
	private List<Actividad> actividad;
	
	public Equipoprueba() {
		// TODO Auto-generated constructor stub
	}
		
	public long getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Actividad> getActividad() {
		return actividad;
	}

	public void setActividad(List<Actividad> actividad) {
		this.actividad = actividad;
	}

	private static final long serialVersionUID = 1L;

}
