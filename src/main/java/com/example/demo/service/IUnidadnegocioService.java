package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Unidadnegocio;

public interface IUnidadnegocioService {
	
	public List<Unidadnegocio> findAll();

	public Unidadnegocio save(Unidadnegocio unidadnegocio);
	
	public Unidadnegocio findById(Long id);
	
	public void delete(Long id);

}
