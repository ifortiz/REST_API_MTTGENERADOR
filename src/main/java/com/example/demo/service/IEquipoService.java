package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Equipo;

public interface IEquipoService {
	
	public List<Equipo> findAll();

	public Equipo save(Equipo equipo);
	
	public Equipo findById(Long id);
	

	
	public void delete(Long id);

}
