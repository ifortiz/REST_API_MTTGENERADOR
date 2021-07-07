package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ReporteEquipo {
	Equipo equipo;
	ArrayList<OrdenTrabajo> equipogeneral;
	public Equipo getEquipo() {
		return equipo;
	}
	public ReporteEquipo() {
		if(equipo==null)
		equipo=new Equipo();
		if(equipogeneral==null)
			equipogeneral=new ArrayList<>();
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public void setEquipogeneral(ArrayList<OrdenTrabajo> equipogeneral) {
		this.equipogeneral = equipogeneral;
	}
	
	

}
