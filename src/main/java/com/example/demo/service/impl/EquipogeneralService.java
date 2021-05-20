package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Actividad;
import com.example.demo.model.Equipo;
import com.example.demo.model.Equipogeneral;
import com.example.demo.modelDAO.IEquipogeneralDAO;
import com.example.demo.service.IEquipogeneralService;

@Service
public class EquipogeneralService implements IEquipogeneralService {

	@Autowired
	private IEquipogeneralDAO dao;
	
	@Override
	public List<Equipogeneral> findAll() {		
		return (List<Equipogeneral>) dao.findAll();
	}

	@Override
	public Equipogeneral findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public int save(Equipogeneral equipogeneral) {		
		return dao.insertWithOutEntity(equipogeneral.getFecha(),equipogeneral.getOt(),equipogeneral.getUnidad().getId(),equipogeneral.getUsuario().getId());
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Equipogeneral findByOt(Long ot) {
		return dao.findByOt(ot);
	}

	@Override
	public Equipo findByEquipo(Long idEquipo) {

		return dao.getEquipoById(idEquipo);
	}


	
}
