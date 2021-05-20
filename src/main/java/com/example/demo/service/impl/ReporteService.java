package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reporte;
import com.example.demo.modelDAO.IReporteDAO;
import com.example.demo.service.IReporteService;

@Service
public class ReporteService implements IReporteService {
	
	@Autowired
	private IReporteDAO dao;
	
	@Override
	public List<Reporte> findAll() {		
		return (List<Reporte>) dao.findAll();
	}

	@Override
	public Reporte findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Reporte save(Reporte reporte) {		
		return dao.save(reporte);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
