package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Central;
import com.example.demo.modelDAO.ICentralDAO;
import com.example.demo.service.ICentralService;

@Service
public class CentralService implements ICentralService{

	@Autowired
	private ICentralDAO dao;
	
	@Override
	public List<Central> findAll() {		
		return (List<Central>) dao.findAll();
	}

	@Override
	public Central findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Central save(Central central) {		
		return dao.save(central);
	}

	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
