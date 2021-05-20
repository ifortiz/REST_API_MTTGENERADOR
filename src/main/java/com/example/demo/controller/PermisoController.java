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

import com.example.demo.model.Permiso;
import com.example.demo.service.IPermisoService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/api")

public class PermisoController {
	@Autowired
	private IPermisoService service;
	
	
	@GetMapping("/permiso-listar")
	public List<Permiso> listar() {
		return service.findAll();
	}
	
	@GetMapping("/permiso/{nombre}")
	public ResponseEntity<?> getNombre(@PathVariable String nombre) {
		
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			permiso = service.findByNombre(nombre);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(permiso == null){
			response.put("mensaje", "permiso NO EXISTE"+nombre);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
	}
	
		
	@GetMapping("/permiso/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			permiso = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(permiso == null){
			response.put("mensaje", "Permiso NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
	}

	@PostMapping("/permiso")
	public ResponseEntity<?> create(@RequestBody Permiso permiso) {
		Permiso newPermiso = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newPermiso = service.save(permiso);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("permiso", newPermiso);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/permiso/{id}")
	public ResponseEntity<?> update(@RequestBody Permiso permiso, @PathVariable Long id) {

		Permiso currentPermiso = service.findById(id);
		Permiso permisoUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentPermiso == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentPermiso.setNombre(permiso.getNombre());
			permisoUpdated = service.save(currentPermiso);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("permiso", permisoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/permiso/{id}")
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
