package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Unidad;
import com.example.demo.modelDAO.IUnidadDAO;
import com.example.demo.service.IUnidadService;

@Service
public class UnidadService implements IUnidadService {

	@Autowired
	private IUnidadDAO dao;
	
	@Override
	public List<Unidad> findAll() {		
		return (List<Unidad>) dao.findAll();
	}

	@Override
	public Unidad findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Unidad save(Unidad unidad) {		
		return dao.save(unidad);
	}

	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
