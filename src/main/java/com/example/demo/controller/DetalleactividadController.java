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

import com.example.demo.model.Detalleactividad;
import com.example.demo.service.IDetalleactividadService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/detalleactividad")
public class DetalleactividadController {

	@Autowired
	private IDetalleactividadService service;
	
	
	@GetMapping("/listar")
	public List<Detalleactividad> listar() {
		return service.findAll();
	}
			
	@GetMapping("/detalleactividad/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Detalleactividad detalleactividad = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			detalleactividad = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(detalleactividad == null){
			response.put("mensaje", "Detalleactividad NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Detalleactividad>(detalleactividad, HttpStatus.OK);
	}

	@PostMapping("/detalleactividad")
	public ResponseEntity<?> create(@RequestBody Detalleactividad detalleactividad) {
		int newDetalleactividad = 0;

		try {
			newDetalleactividad = service.saveWithOutActividad(detalleactividad);
		} catch (DataAccessException e) {

			return ResponseEntity.ok(e.getMessage());
		}
	
		return ResponseEntity.ok(newDetalleactividad+"");
	}
	@PostMapping("/detalleListActividad")
	public ResponseEntity<?> createListActividad(@RequestBody List<Detalleactividad> detalleactividad) {
		int newDetalleactividad = 0;

		try {
		    for (Detalleactividad detalle: detalleactividad) {
		    	service.saveWithOutActividad(detalle);
		      }
				newDetalleactividad=1;
			
		} catch (DataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.ok(newDetalleactividad+"");
	}
	
	@PostMapping("/detalleactividad/full")
	public ResponseEntity<?> createfull(@RequestBody Detalleactividad detalleactividad) {
		Detalleactividad newDetalleactividad = null;

		try {
			newDetalleactividad = service.save(detalleactividad);
		} catch (DataAccessException e) {

			return ResponseEntity.ok(e.getMessage());
		}
	
		return ResponseEntity.ok(newDetalleactividad+"");
	}
	
	@PutMapping("/detalleactividad/{id}")
	public ResponseEntity<?> update(@RequestBody Detalleactividad detalleactividad, @PathVariable Long id) {

		Detalleactividad currentDetalleactividad = service.findById(id);
		Detalleactividad detalleactividadUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentDetalleactividad == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentDetalleactividad.setNomprueba(detalleactividad.getNomprueba());
			currentDetalleactividad.setValorprueba(detalleactividad.getValorprueba());
		
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("detalleactividad", detalleactividadUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/detalleactividad/{id}")
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
