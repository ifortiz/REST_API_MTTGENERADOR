package com.example.demo.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Equipogeneral implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long ot;
	private Date fecha;
	
	public Equipogeneral() {
		// TODO Auto-generated constructor stub
	}
	
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	private Usuario usuario;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reporte", referencedColumnName = "id")
	private Reporte reporte;
	
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unidad", referencedColumnName = "id")
	private Unidad unidad;
	
	
	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOt() {
		return ot;
	}

	public void setOt(long ot) {
		this.ot = ot;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}

	private static final long serialVersionUID = 1L;

}
