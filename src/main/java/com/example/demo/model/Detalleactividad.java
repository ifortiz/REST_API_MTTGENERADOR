package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Detalleactividad implements Serializable {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomprueba;
	private String valorprueba;
	
	public Detalleactividad() {
		// TODO Auto-generated constructor stub
	}
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
	private Actividad actividad;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne    ( cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })


	public Long getId() {
		return id;
	}
	
	public String getNomprueba() {
		return nomprueba;
	}
	
	public void setNomprueba(String nomprueba) {
		this.nomprueba = nomprueba;
	}
	
	public String getValorprueba() {
		return valorprueba;
	}
	
	public void setValorprueba(String valorprueba) {
		this.valorprueba = valorprueba;
	}
	
	public Actividad getActividad() {
		return actividad;
	}
	
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
}
