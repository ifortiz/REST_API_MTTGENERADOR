package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Detalleactividad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomprueba;
	private Double valorprueba;
	
	public Detalleactividad() {
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne    ( cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
	private Actividad actividad;
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}
	
	public String getNomprueba() {
		return nomprueba;
	}
	
	public void setNomprueba(String nomprueba) {
		this.nomprueba = nomprueba;
	}
	
	public Double getValorprueba() {
		return valorprueba;
	}
	
	public void setValorprueba(Double valorprueba) {
		this.valorprueba = valorprueba;
	}
	
	public Actividad getActividad() {
		return actividad;
	}
	
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
}
