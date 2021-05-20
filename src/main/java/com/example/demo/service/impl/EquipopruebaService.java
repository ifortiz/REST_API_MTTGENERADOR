package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Equipoprueba;
import com.example.demo.modelDAO.IEquipopruebaDAO;
import com.example.demo.service.IEquipopruebaService;

@Service
public class EquipopruebaService implements IEquipopruebaService {
	
	@Autowired
	private IEquipopruebaDAO dao;
	
	@Override
	public List<Equipoprueba> findAll() {		
		return (List<Equipoprueba>) dao.findAll();
	}

	@Override
	public Equipoprueba findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Equipoprueba save(Equipoprueba equipoprueba) {		
		return dao.save(equipoprueba);
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
