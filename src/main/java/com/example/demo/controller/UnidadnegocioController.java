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

import com.example.demo.model.Unidadnegocio;
import com.example.demo.service.IUnidadnegocioService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/unidadnegocio")

public class UnidadnegocioController {
	
	@Autowired
	private IUnidadnegocioService service;
	
	
	@GetMapping("/listar")
	public List<Unidadnegocio> listar() {
		return service.findAll();
	}
	
	
		
	@GetMapping("/unidadnegocio/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Unidadnegocio unidadnegocio = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			unidadnegocio = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(unidadnegocio == null){
			response.put("mensaje", "Unidadnegocio NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Unidadnegocio>(unidadnegocio, HttpStatus.OK);
	}

	@PostMapping("/unidadnegocio")
	public ResponseEntity<?> create(@RequestBody Unidadnegocio unidadnegocio) {
		Unidadnegocio newUnidadnegocio = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newUnidadnegocio = service.save(unidadnegocio);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("unidadnegocio", newUnidadnegocio);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/unidadnegocio/{id}")
	public ResponseEntity<?> update(@RequestBody Unidadnegocio unidadnegocio, @PathVariable Long id) {

		Unidadnegocio currentUnidadnegocio = service.findById(id);
		Unidadnegocio unidadnegocioUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentUnidadnegocio == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentUnidadnegocio.setNombre(unidadnegocio.getNombre());
			unidadnegocioUpdated = service.save(currentUnidadnegocio);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("unidadnegocio", unidadnegocioUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/unidadnegocio/{id}")
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
