package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sistema;
import com.example.demo.modelDAO.ISistemaDAO;
import com.example.demo.service.ISistemaService;

@Service
public class SistemaService implements ISistemaService  {
	
	@Autowired
	private ISistemaDAO dao;
	
	@Override
	public List<Sistema> findAll() {		
		return (List<Sistema>) dao.findAll();
	}

	@Override
	public Sistema findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Sistema save(Sistema sistema) {		
		return dao.save(sistema);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
