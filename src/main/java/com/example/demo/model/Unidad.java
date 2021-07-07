package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Unidad implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private long centralId;
	
	public Unidad() {
		// TODO Auto-generated constructor stub
	}
	
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "central", referencedColumnName = "id")
		private Central central;
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Central getCentral() {
		return central;
	}

	public void setCentral(Central central) {
		this.central = central;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCentralId() {
		return centralId;
	}

	public void setCentralId(long centralId) {
		this.centralId = centralId;
	}

	private static final long serialVersionUID = 1L;

}
