package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Actividadequipo;

public interface IActividadequipoService {
	
	public List<Actividadequipo> findAll();

	public Actividadequipo save(Actividadequipo actividadequipo);
	
	public Actividadequipo findById(Long id);
	
	public void delete(Long id);
}
