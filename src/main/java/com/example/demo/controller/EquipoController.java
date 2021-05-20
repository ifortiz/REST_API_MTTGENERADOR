package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Equipo;
import com.example.demo.service.IEquipoService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/equipo")
public class EquipoController {

	@Autowired
	private IEquipoService service;
	
	
	@GetMapping("/listar")
	public List<Equipo> listar() {
		return service.findAll();
	}
			
	@GetMapping("/equipo/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Equipo equipo = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			equipo = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(equipo == null){
			response.put("mensaje", "Equipo NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);
	}

	@PostMapping("/equipo")
	public ResponseEntity<?> create(@RequestBody Equipo equipo) {
		Equipo newEquipo = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newEquipo = service.save(equipo);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("equipo", newEquipo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/equipo/{id}")
	public ResponseEntity<?> update(@RequestBody Equipo equipo, @PathVariable Long id) {

		Equipo currentEquipo = service.findById(id);
		Equipo equipoUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentEquipo == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentEquipo.setDescripcion(equipo.getDescripcion());
			currentEquipo.setPotencia(equipo.getPotencia());
			currentEquipo.setModelo(equipo.getModelo());
			currentEquipo.setCorrientenom(equipo.getCorrientenom());
			currentEquipo.setSerie(equipo.getSerie());
			currentEquipo.setTipo(equipo.getTipo());
			currentEquipo.setVoltaje(equipo.getVoltaje());
			currentEquipo.setIdEquipoGeneral(equipo.getIdEquipoGeneral());
			equipoUpdated = service.save(currentEquipo);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("equipo", equipoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/equipo/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		try {
			service.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje","ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "BORRADO CORRECTAMENTE");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
