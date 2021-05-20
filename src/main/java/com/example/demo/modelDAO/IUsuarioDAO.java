package com.example.demo.modelDAO;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
	public Usuario findByCorreo(String email);
	
}
