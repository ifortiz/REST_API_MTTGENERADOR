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

import com.example.demo.model.Actividad;
import com.example.demo.service.IActividadService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/actividad")
public class ActividadController {
	
	@Autowired
	private IActividadService service;
	
	
	@GetMapping("/listar")
	public List<Actividad> listar() {
		return service.findAll();
	}
			
	@GetMapping("/actividadid/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Actividad actividad = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			actividad = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(actividad == null){
			response.put("mensaje", "Actividad NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Actividad>(actividad, HttpStatus.OK);
	}
	
	@GetMapping("/actividadequipo/{id}")
	public ResponseEntity<?> getActividadByEquipo(@PathVariable Long id) {
		
		List<Long> num_actividades = null;
		try{
			num_actividades = service.findByEquipo(id);
			if(num_actividades!=null && num_actividades.size()>0) { 
				long max=num_actividades.get(0);
				return ResponseEntity.ok(max);
			}
			else 
				return ResponseEntity.ok(0);
		} catch(DataAccessException e){
			return ResponseEntity.ok(e.getMessage());
		}
		
	}

	@PostMapping("/actividad")
	public ResponseEntity<?> createWitOutDetalleActividad(@RequestBody Actividad actividad) {
		int newActividad = 0;
		Actividad findActividadInsert=null;

		try {
			newActividad = service.saveWithOutDetalleActividad(actividad);
			if(newActividad == 1) {
				findActividadInsert=service.findLast();
			}
				
		} catch (DataAccessException e) {
			return ResponseEntity.ok(e.getMessage());
		}


		return ResponseEntity.ok(findActividadInsert);
	}


	/*
	@PutMapping("/actividad/{id}")
	public ResponseEntity<?> update(@RequestBody Actividad actividad, @PathVariable Long id) {

		Actividad currentActividad = service.findById(id);
		Actividad actividadUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentActividad == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentActividad.setDescripcion(actividad.getDescripcion());
			currentActividad.setCumplimiento(actividad.getCumplimiento());
			currentActividad.setObservacion(actividad.getObservacion());
			currentActividad.setPermitivo(actividad.getPermitivo());
			currentActividad.setUsuarioact(actividad.getUsuarioact());
			currentActividad.setUsuariomod(actividad.getUsuariomod());
			actividadUpdated = service.save(currentActividad);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("actividad", actividadUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	*/
	
	@DeleteMapping("/actividad/{id}")
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
