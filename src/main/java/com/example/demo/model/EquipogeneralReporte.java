package com.example.demo.model;

import java.util.List;

public class EquipogeneralReporte {
	OrdenTrabajo equipogeneral;
	List<Actividad> actividades;
	public OrdenTrabajo getEquipogeneral() {
		return equipogeneral;
	}
	public void setEquipogeneral(OrdenTrabajo equipogeneral) {
		this.equipogeneral = equipogeneral;
	}
	public List<Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
}
