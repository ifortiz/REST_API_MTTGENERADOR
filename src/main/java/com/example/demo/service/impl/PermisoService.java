package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Permiso;
import com.example.demo.modelDAO.IPermisoDAO;
import com.example.demo.service.IPermisoService;

@Service
public class PermisoService implements IPermisoService {
	
	@Autowired
	private IPermisoDAO dao;
	
	@Override
	public List<Permiso> findAll() {		
		return (List<Permiso>) dao.findAll();
	}

	@Override
	public Permiso findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Permiso save(Permiso permiso) {		
		return dao.save(permiso);
	}

	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	
	@Override
	public Permiso findByNombre(String nombre) {
		return dao.findByNombre(nombre);
	}

}
