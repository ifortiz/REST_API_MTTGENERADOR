package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.modelDAO.IUsuarioDAO;
import com.example.demo.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDAO dao;
	
	@Override
	public List<Usuario> findAll() {		
		return (List<Usuario>) dao.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Usuario save(Usuario usuario) {		
		return dao.save(usuario);
	}

	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	
	@Override
	public Usuario findByEmail(String email) {
		return dao.findByCorreo(email);
	}


}

