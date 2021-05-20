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
public class Central implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	public Central() {
		// TODO Auto-generated constructor stub
	}
	
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unidadnegocio", referencedColumnName = "id")
	private Unidadnegocio unidadnegocio;
	
	
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Unidadnegocio getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(Unidadnegocio unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}



	public void setId(Long id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	

}
