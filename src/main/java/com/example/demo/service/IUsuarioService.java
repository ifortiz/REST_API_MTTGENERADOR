package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;

public interface IUsuarioService  {

	
	public List<Usuario> findAll();

	public Usuario save(Usuario usuario);
	
	public Usuario findById(Long id);
	

	
	public void delete(Long id);
	
	public Usuario findByEmail(String email);
}

