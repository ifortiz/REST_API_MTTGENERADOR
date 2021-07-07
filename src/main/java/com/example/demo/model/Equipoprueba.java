package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipoprueba implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
    @Column(name = "marca", updatable = false, nullable = false)
	private String marca;
    @Column(name = "modelo", updatable = false, nullable = false)
	private String modelo;
    @Column(name = "tipo", updatable = false, nullable = false)
	private String tipo;
	

	public Equipoprueba() {
		// TODO Auto-generated constructor stub
	}
	
	
		
	


	public void setId(Long id) {
		this.id = id;
	}



	public Long getId() {
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

	//private static final Long serialVersionUID = 1L;

}
