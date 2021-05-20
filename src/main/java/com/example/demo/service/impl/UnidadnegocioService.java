package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Unidadnegocio;
import com.example.demo.modelDAO.IUnidadnegocioDAO;
import com.example.demo.service.IUnidadnegocioService;

@Service
public class UnidadnegocioService implements IUnidadnegocioService {
	
	@Autowired
	private IUnidadnegocioDAO dao;
	
	@Override
	public List<Unidadnegocio> findAll() {		
		return (List<Unidadnegocio>) dao.findAll();
	}

	@Override
	public Unidadnegocio findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Unidadnegocio save(Unidadnegocio unidadnegocio) {		
		return dao.save(unidadnegocio);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
