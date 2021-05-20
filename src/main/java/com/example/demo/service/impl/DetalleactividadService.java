package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Detalleactividad;
import com.example.demo.modelDAO.IDetalleactividadDAO;
import com.example.demo.service.IDetalleactividadService;

@Service
public class DetalleactividadService implements IDetalleactividadService {
	@Autowired
	private IDetalleactividadDAO dao;
	
	@Override
	public List<Detalleactividad> findAll() {		
		return (List<Detalleactividad>) dao.findAll();
	}

	@Override
	public Detalleactividad findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public int saveWithOutActividad(Detalleactividad detalleactividad) {

		return dao.saveWithOutActividad(detalleactividad.getNomprueba(),detalleactividad.getValorprueba(),detalleactividad.getActividad().getId());
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Detalleactividad save(Detalleactividad detalleactividad) {
		// TODO Auto-generated method stub
		return dao.save(detalleactividad);
	}

}
