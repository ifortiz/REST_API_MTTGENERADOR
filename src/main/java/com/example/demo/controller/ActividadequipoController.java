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

import com.example.demo.model.Actividadequipo;
import com.example.demo.service.IActividadequipoService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/actividadequipo")

public class ActividadequipoController {

	@Autowired
	private IActividadequipoService service;
	
	
	@GetMapping("/listar")
	public List<Actividadequipo> listar() {
		return service.findAll();
	}
			
	@GetMapping("/actividadequipo/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Actividadequipo actividadequipo = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			actividadequipo = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(actividadequipo == null){
			response.put("mensaje", "Actividadequipo NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Actividadequipo>(actividadequipo, HttpStatus.OK);
	}

	@PostMapping("/Actividadequipo")
	public ResponseEntity<?> create(@RequestBody Actividadequipo actividadequipo) {
		Actividadequipo newActividadequipo = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newActividadequipo = service.save(actividadequipo);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("actividadequipo", newActividadequipo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/actividadequipo/{id}")
	public ResponseEntity<?> update(@RequestBody Actividadequipo actividadequipo, @PathVariable Long id) {

		Actividadequipo currentActividadequipo = service.findById(id);
		Actividadequipo ActividadequipoUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentActividadequipo == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentActividadequipo.setNumactividad(actividadequipo.getNumactividad());
			currentActividadequipo.setDescripcion(actividadequipo.getDescripcion());
			

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("actividadequipo", ActividadequipoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/actividadequipo/{id}")
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
