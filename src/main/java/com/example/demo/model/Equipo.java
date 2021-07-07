package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Equipo implements Serializable {

	@Id
	 @Column(name = "idcod", updatable = false, nullable = false)
	private Long idcod;
	 @Column(name = "descripcion", updatable = false, nullable = false)
	private String descripcion;
	 @Column(name = "potencia", updatable = false, nullable = false)
	private String potencia;
	 @Column(name = "modelo", updatable = false, nullable = false)
	private String modelo;
	 @Column(name = "corrientenom", updatable = false, nullable = false)
	private String corrientenom;
	 @Column(name = "serie", updatable = false, nullable = false)
	private String serie;
	 @Column(name = "tipo", updatable = false, nullable = false)
	private String tipo;
	 @Column(name = "voltaje", updatable = false, nullable = false)
	private String voltaje;
	private long sistemaId;
	
	public Equipo() {
		// TODO Auto-generated constructor stub
	}
	// LA TABLA USUARIO TENDRA EL ID DE LA OTRA TABLA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sistema", referencedColumnName = "id", updatable = false)
	private Sistema sistema;
		

	/*private Long equipogeneral;
	
	
	public Long getIdEquipoGeneral() {
		return equipogeneral;
	}

	public void setIdEquipoGeneral(Long equipogeneral) {
		this.equipogeneral = equipogeneral;
	}*/

	public Long getIdcod() {
		return idcod;
	}

	public void setIdcod(Long idcod) {
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

		public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}
		private static final long serialVersionUID = 1L;

}
