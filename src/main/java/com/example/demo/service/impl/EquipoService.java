package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Equipo;
import com.example.demo.modelDAO.IEquipoDAO;
import com.example.demo.service.IEquipoService;

@Service
public class EquipoService implements IEquipoService{

	@Autowired
	private IEquipoDAO dao;
	
	@Override
	public List<Equipo> findAll() {		
		return (List<Equipo>) dao.findAll();
	}

	@Override
	public Equipo findById(Long id) {
		return dao.findOneByidcod(id);
	}

	@Override
	public Equipo save(Equipo equipo) {		
		return dao.save(equipo);
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}


}
