package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Perfil;


public interface IPerfilService {
	
	public List<Perfil> findAll();

	public Perfil save(Perfil perfil);
	
	public Perfil findById(Long id);
	
	public void delete(Long id);

}
