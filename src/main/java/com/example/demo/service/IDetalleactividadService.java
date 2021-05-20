package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Detalleactividad;

public interface IDetalleactividadService {
	
	public List<Detalleactividad> findAll();

	public int saveWithOutActividad(Detalleactividad detalleactividad);
	
	public Detalleactividad save(Detalleactividad detalleactividad);
	
	public Detalleactividad findById(Long id);
	
	public void delete(Long id);

}
