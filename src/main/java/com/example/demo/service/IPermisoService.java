package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Permiso;

public interface IPermisoService {
	
	public List<Permiso> findAll();

	public Permiso save(Permiso permiso);
	
	public Permiso findById(Long id);
	
	public Permiso findByNombre(String nombre);
	
	public void delete(Long id);

}
