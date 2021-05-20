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
import com.example.demo.model.Equipogeneral;
import com.example.demo.service.IEquipogeneralService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/equipogeneral")
public class EquipogeneralController {

	@Autowired
	private IEquipogeneralService service;

	@GetMapping("/listar")
	public List<Equipogeneral> listar() {
		return service.findAll();
	}
			
	@GetMapping("/equipogeneralid/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Equipogeneral equipogeneral = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			equipogeneral = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(equipogeneral == null){
			response.put("mensaje", "Equipogeneral NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Equipogeneral>(equipogeneral, HttpStatus.OK);
	}
	@GetMapping("/equipogeneralot/{ot}")
	public ResponseEntity<?> ordenTrabajo(@PathVariable Long ot) {
		
		Equipogeneral equipogeneral = null;
		Equipo equipo=null;
		try{
			equipogeneral = service.findByOt(ot);
			if(equipogeneral!=null) {
				equipo= service.findByEquipo(equipogeneral.getId());
				if(equipo!=null) {
				return ResponseEntity.ok(equipo);
				}else {
					equipo=new Equipo();
					equipo.setIdcod(500);
					equipo.setDescripcion("ERROR, EQUIPO NO ESTA RELACIONADO CON NINGUN EQUIPO GENERAL");
					return ResponseEntity.ok(equipo);
				}
			}else {
				equipo=new Equipo();
				equipo.setIdcod(0);
				equipo.setDescripcion("OT NO ENCONTRADO");
				return ResponseEntity.ok(equipo);
			}

			/*
			if(equipogeneral!=null) {
				equipo=service.findByEquipo(equipogeneral.getId());
				if(equipo!=null) {
					return ResponseEntity.ok(equipo);
				}
			}
			
				equipo=new Equipo();
				equipo.setIdcod(0);
				return ResponseEntity.ok(equipo);*/
		
		} catch(DataAccessException e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	

	}

	@PostMapping("/equipogeneral")
	public ResponseEntity<?> create(@RequestBody Equipogeneral equipogeneral) {
		int newEquipogeneral = -59;

	
		try {
			newEquipogeneral = service.save(equipogeneral);
			return ResponseEntity.ok(newEquipogeneral);
		} catch (DataAccessException e) {
			return ResponseEntity.ok(e.getMessage());
		}

	
	}
	
	@PutMapping("/equipogeneral/{id}")
	public ResponseEntity<?> update(@RequestBody Equipogeneral equipogeneral, @PathVariable Long id) {

		Equipogeneral currentEquipogeneral = service.findById(id);
		Equipogeneral equipogeneralUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentEquipogeneral == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentEquipogeneral.setOt(equipogeneral.getOt());
			currentEquipogeneral.setFecha(equipogeneral.getFecha());
			
		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("equipogeneral", equipogeneralUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/equipogeneral/{id}")
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
