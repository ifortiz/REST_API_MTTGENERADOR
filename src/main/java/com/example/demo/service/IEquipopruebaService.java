package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EquipoPruebar;
import com.example.demo.model.Equipoprueba;

public interface IEquipopruebaService {
	
	public List<Equipoprueba> findAll();
	public List<Equipoprueba> findAll2();

	public Equipoprueba save(Equipoprueba equipoprueba);
	
	public Equipoprueba findById(Long id);
	
	public void delete(Long id);

}
