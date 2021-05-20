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

import com.example.demo.model.Perfil;
import com.example.demo.service.IPerfilService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/perfil")

public class PerfilController {
	
	@Autowired
	private IPerfilService service;
	
	
	@GetMapping("/listar")
	public List<Perfil> listar() {
		return service.findAll();
	}
	
	@GetMapping("/calcule/{value}")
	public ResponseEntity<?> calcule(@PathVariable Long value) {
		
		String format = "";
		
		if(value < 5) {
			format = "ALERTA";
		}else {
			format = "TODO OK";
		}
		
		return new ResponseEntity<String>(format, HttpStatus.OK);
	}
		
	@GetMapping("/perfil/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Perfil perfil = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			perfil = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(perfil == null){
			response.put("mensaje", "Perfil NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}

	@PostMapping("/perfil")
	public ResponseEntity<?> create(@RequestBody Perfil perfil) {
		Perfil newPerfil = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newPerfil = service.save(perfil);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("perfil", newPerfil);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/perfil/{id}")
	public ResponseEntity<?> update(@RequestBody Perfil perfil, @PathVariable Long id) {

		Perfil currentPerfil = service.findById(id);
		Perfil perfilUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentPerfil == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentPerfil.setNombre(perfil.getNombre());
			perfilUpdated = service.save(currentPerfil);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("perfil", perfilUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/perfil/{id}")
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
