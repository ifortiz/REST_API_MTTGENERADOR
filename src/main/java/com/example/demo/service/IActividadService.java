package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Actividad;

public interface IActividadService {

	public List<Actividad> findAll();

	public int saveWithOutDetalleActividad(Actividad actividad);
	
	public Actividad findLast();
	
	public Actividad findById(Long id);
	
	public void delete(Long id);
	
	public List<Long>  findByEquipo(Long id);
}
