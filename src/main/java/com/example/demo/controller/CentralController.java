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

import com.example.demo.model.Central;
import com.example.demo.model.Unidadnegocio;
import com.example.demo.service.ICentralService;
import com.example.demo.service.IUnidadnegocioService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/central")

public class CentralController {
	
	@Autowired
	private ICentralService service;
		@Autowired
	private IUnidadnegocioService unidadnegocioService;
	
	
	@GetMapping("/listar")
	public List<Central> listar() {
		return service.findAll();
	}
	
	@GetMapping("/central/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Central central = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			central = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(central == null){
			response.put("mensaje", "central NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Central>(central, HttpStatus.OK);
	}

	@PostMapping("/central")
	public ResponseEntity<?> create(@RequestBody Central central) {
		Central newCentral = null;

		Map<String, Object> response = new HashMap<>();
		try {

									//para llaves foraneas
			Unidadnegocio unidadnegocio = unidadnegocioService.findById(central.getUnidadnegocio().getId());
				if(unidadnegocio != null) {
					central.setUnidadnegocio(unidadnegocio);
				} else {
					central.setUnidadnegocio(null);
			}
			newCentral = service.save(central);
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CREADO CORRECTAMENTE");
		response.put("central", newCentral);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/central/{id}")
	public ResponseEntity<?> update(@RequestBody Central central, @PathVariable Long id) {

		Central currentCentral = service.findById(id);
		Central centralUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentCentral == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentCentral.setNombre(central.getNombre());

									Unidadnegocio unidadnegocio = unidadnegocioService.findById(central.getUnidadnegocio().getId());
			if(unidadnegocio != null) {
				currentCentral.setUnidadnegocio(unidadnegocio);
			}else {
				currentCentral.setUnidadnegocio(null);
			}
			
			centralUpdated = service.save(currentCentral);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("central", centralUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/central/{id}")
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
