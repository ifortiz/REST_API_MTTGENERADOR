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

import com.example.demo.model.EquipoPruebar;
import com.example.demo.model.Equipoprueba;
import com.example.demo.service.IEquipopruebaService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/equipoprueba")
public class EquipopruebaController {
	
	@Autowired
	private IEquipopruebaService service;
	
	
	@GetMapping("/listar")
	public List<Equipoprueba> listar() {
		return service.findAll();
	}
			
	@GetMapping("/listar2")
	public List<Equipoprueba> listar2() {
		return service.findAll2();
	}
	@GetMapping("/equipoprueba/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Equipoprueba equipoprueba = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			equipoprueba = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(equipoprueba == null){
			response.put("mensaje", "Equipoprueba NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Equipoprueba>(equipoprueba, HttpStatus.OK);
	}

	@PostMapping("/equipoprueba")
	public ResponseEntity<?> create(@RequestBody Equipoprueba equipoprueba) {
		Equipoprueba newEquipoprueba = null;

		Map<String, Object> response = new HashMap<>();
		try {
			newEquipoprueba = service.save(equipoprueba);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("equipoprueba", newEquipoprueba);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/equipoprueba/{id}")
	public ResponseEntity<?> update(@RequestBody Equipoprueba equipoprueba, @PathVariable Long id) {

		Equipoprueba currentEquipoprueba = service.findById(id);
		Equipoprueba equipopruebaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentEquipoprueba == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentEquipoprueba.setMarca(equipoprueba.getMarca());
			currentEquipoprueba.setModelo(equipoprueba.getModelo());
			currentEquipoprueba.setTipo(equipoprueba.getTipo());
			equipopruebaUpdated = service.save(currentEquipoprueba);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("equipoprueba", equipopruebaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/equipoprueba/{id}")
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
