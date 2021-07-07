package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Actividad;
import com.example.demo.model.Equipo;
import com.example.demo.model.OrdenTrabajo;
import com.example.demo.modelDAO.IOrdenTrabajoDAO;
import com.example.demo.service.IOrdenTrabajoService;

@Service
public class OrdenTrabajoService implements IOrdenTrabajoService {

	@Autowired
	private IOrdenTrabajoDAO dao;
	
	@Override
	public List<OrdenTrabajo> findAll() {		
		return (List<OrdenTrabajo>) dao.findAll();
	}

	@Override
	public OrdenTrabajo findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public int save2(OrdenTrabajo equipogeneral) {		
		System.out.println(equipogeneral.getOt());
		return dao.insertWithOutEntity(equipogeneral.getFecha(),equipogeneral.getOt(),equipogeneral.getUnidad().getId(),equipogeneral.getUsuario().getId(),equipogeneral.getIdEquipo());
	}
	@Override
	public OrdenTrabajo saveentity(OrdenTrabajo equipogeneral) {		
		return dao.save(equipogeneral);
	}
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public OrdenTrabajo findByOt(Long ot) {
		return dao.findByOt(ot);
	}

	/*@Override
	public Equipo findByEquipo(Long idEquipo) {

		return dao.getEquipoById(idEquipo);
	}*/




	
}
