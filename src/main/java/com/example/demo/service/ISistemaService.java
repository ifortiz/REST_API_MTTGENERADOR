package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Sistema;

public interface ISistemaService {
	
	public List<Sistema> findAll();

	public Sistema save(Sistema sistema);
	
	public Sistema findById(Long id);
	
	public void delete(Long id);

}
