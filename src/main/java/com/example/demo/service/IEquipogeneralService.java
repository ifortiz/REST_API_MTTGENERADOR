package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Actividad;
import com.example.demo.model.Equipo;
import com.example.demo.model.Equipogeneral;

public interface IEquipogeneralService {
	
	public List<Equipogeneral> findAll();

	public int save(Equipogeneral equipogeneral);
	
	public Equipogeneral findById(Long id);
	
	public Equipogeneral findByOt(Long ot);
	
	public Equipo findByEquipo(Long idEquipo);
	
	public void delete(Long id);

	

}
