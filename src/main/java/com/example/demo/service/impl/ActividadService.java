package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Actividad;
import com.example.demo.modelDAO.IActividadDAO;
import com.example.demo.service.IActividadService;

@Service
public class ActividadService implements IActividadService{
	@Autowired
	private IActividadDAO dao;
	
	@Override
	public List<Actividad> findAll() {		
		return (List<Actividad>) dao.findAll();
	}

	@Override
	public Actividad findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public int saveWithOutDetalleActividad(Actividad actividad) {		
		return dao.insertWithOutDetalleActividad(actividad.getCumplimiento(),actividad.getDescripcion()
				,actividad.getNumeroAct(),actividad.getObservacion(),
				actividad.getPermitivo(),actividad.getUsuarioact(),actividad.getUsuariomod(),actividad.getEquipo().getIdcod(),actividad.getFcreacion());
	}


	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public  List<Long> findByEquipo(Long id) {
		return dao.findByEquipo(id);
		
	}

	@Override
	public Actividad findLast() {
		// TODO Auto-generated method stub
		return dao.findTopByOrderByIdDesc();
	}

}
