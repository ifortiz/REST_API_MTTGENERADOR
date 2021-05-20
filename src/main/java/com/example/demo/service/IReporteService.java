package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Reporte;

public interface IReporteService {
	
	public List<Reporte> findAll();

	public Reporte save(Reporte reporte);
	
	public Reporte findById(Long id);
	
	public void delete(Long id);

}
