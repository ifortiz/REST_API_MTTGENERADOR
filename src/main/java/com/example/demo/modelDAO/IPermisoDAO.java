package com.example.demo.modelDAO;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Permiso;

public interface IPermisoDAO extends CrudRepository<Permiso, Long> {
	public Permiso findByNombre(String nombre);
}
