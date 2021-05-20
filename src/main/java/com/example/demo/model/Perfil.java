package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Perfil implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	public Perfil() {
	}
	
	// SE CREARA TABLA INTERMEDIA CON LA RELACION DE AMBAS TABLAS
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(name = "perfil_permiso", joinColumns = @JoinColumn(name = "id_perfil"), inverseJoinColumns = @JoinColumn(name = "id_permiso"), uniqueConstraints = {
		@UniqueConstraint(columnNames = { "id_perfil", "id_permiso" }) })
		private List<Permiso> permisos;
		
	
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	private static final long serialVersionUID = 1L;
	
}
