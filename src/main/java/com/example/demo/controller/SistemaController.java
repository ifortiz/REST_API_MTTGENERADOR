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

import com.example.demo.model.Sistema;
import com.example.demo.service.ISistemaService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/sistema")

public class SistemaController {

	@Autowired
	private ISistemaService service;
	
	
	@GetMapping("/listar")
	public List<Sistema> listar() {
		return service.findAll();
	}
	
	
		
	@GetMapping("/sistema/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Sistema sistema = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			sistema = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(sistema == null){
			response.put("mensaje", "Sistema NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Sistema>(sistema, HttpStatus.OK);
	}

	@PostMapping("/sistema")
	public ResponseEntity<?> create(@RequestBody Sistema sistema) {
		Sistema newSistema = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newSistema = service.save(sistema);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("sistema", newSistema);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/sistema/{id}")
	public ResponseEntity<?> update(@RequestBody Sistema sistema, @PathVariable Long id) {

		Sistema currentSistema = service.findById(id);
		Sistema sistemaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentSistema == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentSistema.setNombre(sistema.getNombre());
			sistemaUpdated = service.save(currentSistema);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("sistema", sistemaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/sistema/{id}")
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
