package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Actividad;
import com.example.demo.model.EmailActividades;
import com.example.demo.model.OrdenTrabajo;

public interface IActividadService {

	public List<Actividad> findAll();

	public int saveWithOutDetalleActividad(Actividad actividad);
	
	public Actividad saveWithDetalleActividad(Actividad actividad);
	
	public Actividad findLast();
	
	public Actividad findById(Long id);
	
	public void delete(Long id);
	
	public List<Actividad>  findByEquipo(Long id);
	
	public List<Long>  findByIdEquipoGeneral(Long id);
	
	public List<Actividad>  findByOt(Long id);
	
	public int updateIdEquipogeneral(Long idActividad,Long idEquipogeneral);
	
	public int updateIdEquipogeneralPrueba(Long idActividad,Long idEquipogeneral,Long idEquipoPrueba);
	
	public boolean enviarEmail(EmailActividades email);
}
