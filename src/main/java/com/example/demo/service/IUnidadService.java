package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Unidad;

public interface IUnidadService {
	
	public List<Unidad> findAll();

	public Unidad save(Unidad unidad);
	
	public Unidad findById(Long id);
	
	public void delete(Long id);

}
