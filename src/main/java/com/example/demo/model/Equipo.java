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
public class Equipo implements Serializable {

	@Id

	private long idcod;
	private String descripcion;
	private String potencia;
	private String modelo;
	private String corrientenom;
	private String serie;
	private String tipo;
	private String voltaje;
	
	public Equipo() {
		// TODO Auto-generated constructor stub
	}
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sistema", referencedColumnName = "id")
	private Sistema sistema;
		

	private Long equipogeneral;
	
	
	public Long getIdEquipoGeneral() {
		return equipogeneral;
	}

	public void setIdEquipoGeneral(Long equipogeneral) {
		this.equipogeneral = equipogeneral;
	}

	public long getIdcod() {
		return idcod;
	}

	public void setIdcod(long idcod) {
		this.idcod = idcod;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCorrientenom() {
		return corrientenom;
	}

	public void setCorrientenom(String corrientenom) {
		this.corrientenom = corrientenom;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVoltaje() {
		return voltaje;
	}

	public void setVoltaje(String voltaje) {
		this.voltaje = voltaje;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	private static final long serialVersionUID = 1L;

}
