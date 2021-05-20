package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Perfil;
import com.example.demo.modelDAO.IPerfilDAO;
import com.example.demo.service.IPerfilService;


@Service
public class PerfilService implements IPerfilService{
	
	@Autowired
	private IPerfilDAO dao;
	
	@Override
	public List<Perfil> findAll() {		
		return (List<Perfil>) dao.findAll();
	}

	@Override
	public Perfil findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Perfil save(Perfil perfil) {		
		return dao.save(perfil);
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
