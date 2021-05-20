package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String password;
	private String apellido;
	private String correo;
	
	// SE CREARA TABLA INTERMEDIA CON LA RELACION DE AMBAS TABLAS
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_usuario", "id_perfil" }) })
	private List<Perfil> perfiles;
	
//	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "perfil", referencedColumnName = "id")
//	private Perfil perfil;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

	public List<Perfil> getPerfiles() {
		return perfiles;
	}


	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}





	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}





	private static final long serialVersionUID = 1L;

}
