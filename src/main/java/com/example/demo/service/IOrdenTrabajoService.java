package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Actividad;
import com.example.demo.model.Equipo;
import com.example.demo.model.OrdenTrabajo;

public interface IOrdenTrabajoService {
	
	public List<OrdenTrabajo> findAll();

	public int save2(OrdenTrabajo equipogeneral);
	
	public OrdenTrabajo findById(Long id);
	
	public OrdenTrabajo findByOt(Long ot);
	
	/*public Equipo findByEquipo(Long idEquipo);*/
	
	public void delete(Long id);
	
	public OrdenTrabajo saveentity(OrdenTrabajo equipogeneral);
	


}
