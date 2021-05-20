package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Actividadequipo;
import com.example.demo.modelDAO.IActividadequipoDAO;
import com.example.demo.service.IActividadequipoService;

@Service
public class ActividadequipoService implements IActividadequipoService{

	@Autowired
	private IActividadequipoDAO dao;
	
	@Override
	public List<Actividadequipo> findAll() {		
		return (List<Actividadequipo>) dao.findAll();
	}

	@Override
	public Actividadequipo findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Actividadequipo save(Actividadequipo actividadequipo) {		
		return dao.save(actividadequipo);
	}

	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
