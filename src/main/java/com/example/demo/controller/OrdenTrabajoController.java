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
import com.example.demo.model.Equipo;
import com.example.demo.model.OrdenTrabajo;
import com.example.demo.model.Reporte;
import com.example.demo.model.Usuario;
import com.example.demo.service.IActividadService;
import com.example.demo.service.IOrdenTrabajoService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/equipogeneral")
public class OrdenTrabajoController {

	@Autowired
	private IOrdenTrabajoService service;
	@Autowired
	private IActividadService serviceActividad;

	@GetMapping("/listar")
	public List<OrdenTrabajo> listar() {
		return service.findAll();
	}
			
	@GetMapping("/equipogeneralid/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		OrdenTrabajo equipogeneral = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			equipogeneral = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(equipogeneral == null){
			response.put("mensaje", "OrdenTrabajo NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<OrdenTrabajo>(equipogeneral, HttpStatus.OK);
	}
	
	/*save entity*/

	@PutMapping("/updatereporte")
	public ResponseEntity<?> equipoGeneralUpdateReporte( @RequestBody Reporte reporte){

	OrdenTrabajo  equipogeneral= service.findByOt(reporte.getId_ot());
	equipogeneral.setReporte(reporte);
	service.saveentity(equipogeneral);
	return ResponseEntity.ok(equipogeneral);
	}
	
	
	@GetMapping("/equipogeneralot/{ot}")
	public ResponseEntity<?> ordenTrabajo(@PathVariable Long ot) {
		Actividad actividad=new Actividad();
		OrdenTrabajo equipogeneral = null;
		Equipo equipo=null;
		try{
			equipogeneral = service.findByOt(ot);
			if(equipogeneral!=null) {
				List<Actividad> actividades=serviceActividad.findByOt(ot);
				
				if(actividades.size() >0)
					actividad=actividades.get(0);
				else {
					actividad.setId(-1L);

				//	System.out.println(equipogeneral.getIdEquipo());
					actividad.getEquipo().setIdcod(equipogeneral.getIdEquipo());
					//actividad.getEquipo().setIdcod(equipogeneral.getIdEquipo());
	
				}
				
			}else {
				actividad.setId(5000L);
			}

	
		return ResponseEntity.ok(actividad);
		} catch(DataAccessException e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@GetMapping("/equipogeneralbyot/{ot}")
	public ResponseEntity<?> getEquipogeneralByOt(@PathVariable Long ot) {
		
		OrdenTrabajo equipogeneral = null;
		try{
			equipogeneral = service.findByOt(ot);
			return ResponseEntity.ok(equipogeneral);
		
		} catch(DataAccessException e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	


	

	@PostMapping("/equipogeneral")
	public ResponseEntity<?> create(@RequestBody OrdenTrabajo equipogeneral) {
		int newEquipogeneral = -59;

		try {
			newEquipogeneral = service.save2(equipogeneral);
			return ResponseEntity.ok(newEquipogeneral);
		} catch (DataAccessException e) {
			return ResponseEntity.ok(e.getMessage());
		}

	
	}
	
	@PutMapping("/equipogeneral/{id}")
	public ResponseEntity<?> update(@RequestBody OrdenTrabajo equipogeneral, @PathVariable Long id) {

		OrdenTrabajo currentEquipogeneral = service.findById(id);
		OrdenTrabajo equipogeneralUpdated = null;
		
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
