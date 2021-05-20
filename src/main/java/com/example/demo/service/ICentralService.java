package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Central;

public interface ICentralService {
	public List<Central> findAll();

	public Central save(Central central);
	
	public Central findById(Long id);
	
	public void delete(Long id);

}
