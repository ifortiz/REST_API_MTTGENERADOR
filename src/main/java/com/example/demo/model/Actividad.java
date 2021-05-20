package com.example.demo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
public class Actividad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String cumplimiento;
	private String observacion;
	private String permitivo;
	private String usuarioact;
	private String usuariomod;
	private Long numeroAct;
	

	private Calendar fcreacion;
	private Calendar fmodificacion;
	
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipo", referencedColumnName = "idcod",  insertable = false)
	private Equipo equipo;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actividadequipo", referencedColumnName = "id")
	private Actividadequipo actividadequipo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipoprueba", referencedColumnName = "id")
	private Equipoprueba equipoprueba;

	// SE CREARA TABLA INTERMEDIA CON LA RELACION DE AMBAS TABLAS
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "actividad_detalleactividad", joinColumns = @JoinColumn(name = "id_actividad"), inverseJoinColumns = @JoinColumn(name = "id_detalleactividad"), uniqueConstraints = {
	@UniqueConstraint(columnNames = { "id_actividad", "id_detalleactividad" }) })
		private List<Detalleactividad> detalleactividad;
	
	public List<Detalleactividad> getDetalleactividad() {
		return detalleactividad;
	}

	public void setDetalleactividad(List<Detalleactividad> detalleactividad) {
		this.detalleactividad = detalleactividad;
	}

	public Equipoprueba getEquipoprueba() {
		return equipoprueba;
	}

	public void setEquipoprueba(Equipoprueba equipoprueba) {
		this.equipoprueba = equipoprueba;
	}

	public Actividad() {
		// TODO Auto-generated constructor stub
	}
	
		public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCumplimiento() {
		return cumplimiento;
	}

	public void setCumplimiento(String cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getPermitivo() {
		return permitivo;
	}

	public void setPermitivo(String permitivo) {
		this.permitivo = permitivo;
	}

	public String getUsuarioact() {
		return usuarioact;
	}

	public void setUsuarioact(String usuarioact) {
		this.usuarioact = usuarioact;
	}

	public String getUsuariomod() {
		return usuariomod;
	}

	public void setUsuariomod(String usuariomod) {
		this.usuariomod = usuariomod;
	}

	public Calendar getFcreacion() {
		return fcreacion;
	}

	public void setFcreacion(Calendar fcreacion) {
		this.fcreacion = fcreacion;
	}

	public Calendar getFmodificacion() {
		return fmodificacion;
	}

	public void setFmodificacion(Calendar fmodificacion) {
		this.fmodificacion = fmodificacion;
	}

	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}


	public Actividadequipo getActividadequipo() {
		return actividadequipo;
	}

	public void setActividadequipo(Actividadequipo actividadequipo) {
		this.actividadequipo = actividadequipo;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumeroAct() {
		return numeroAct;
	}

	public void setNumeroAct(Long numeroAct) {
		this.numeroAct = numeroAct;
	}


	private static final long serialVersionUID = 1L;

}
