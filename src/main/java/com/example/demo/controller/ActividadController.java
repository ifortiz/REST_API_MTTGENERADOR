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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Actividad;
import com.example.demo.model.EmailActividades;
import com.example.demo.service.IActividadService;
import com.example.demo.service.IEquipoService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/actividad")
public class ActividadController {
	
	@Autowired
	private IActividadService service;
	
	@Autowired
	private IEquipoService serviceEquipo;
	
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
		
		List<Actividad> num_actividades = null;
		try{
			num_actividades = service.findByEquipo(id);
			
			if(num_actividades!=null && num_actividades.size()>0) { 
				Actividad max=num_actividades.get(0);
				return ResponseEntity.ok(max);
			}
			else 
				return ResponseEntity.ok(0);
		} catch(DataAccessException e){
			return ResponseEntity.ok(e.getMessage());
		}
		
	}
	

	@GetMapping("/listarActividad/{id}")
	public ResponseEntity<?> listActividadByEquipo(@PathVariable Long id) {
		
		List<Actividad> num_actividades = null;
		try{
			num_actividades = service.findByEquipo(id);
			return ResponseEntity.ok(num_actividades);
	
		} catch(DataAccessException e){
			return ResponseEntity.notFound().build();
		}
		
	}
	@GetMapping("/listarActividadot/{ot}")
	public ResponseEntity<?> listarActividadByOt(@PathVariable Long ot) {
		List<Actividad> num_actividades = null;
		try{
			num_actividades = service.findByOt(ot);
			return ResponseEntity.ok(num_actividades);
	
		} catch(DataAccessException e){
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping("/email")
	public ResponseEntity<?> listarActividadByOt( @RequestBody  EmailActividades email) {
		boolean resp = service.enviarEmail( email );
		if ( resp)
			return ResponseEntity.ok("200");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
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


	@PostMapping("/actividad/detalleactividad")
	public ResponseEntity<?> savewithDetalleActividad(@RequestBody Actividad actividad) {
	
		Actividad findActividadInsert=null;
		Long idEquipogeneral=actividad.getEquipogeneral().getId();
		actividad.setEquipogeneral(null);
		Long equipoprueba=actividad.getEquipoprueba().getId();
		int resp;
	
		try {
			findActividadInsert= service.saveWithDetalleActividad(actividad);
			findActividadInsert.getEquipogeneral().setId(idEquipogeneral);
			if(equipoprueba==null) {
				resp= service.updateIdEquipogeneral(findActividadInsert.getId(),idEquipogeneral);
				System.out.println("equipoprueba null");
			}else {
				resp= service.updateIdEquipogeneralPrueba(findActividadInsert.getId(),idEquipogeneral,equipoprueba);
				System.out.println("equipoprueba no es  null");
				}
		} catch (DataAccessException e) {
			return ResponseEntity.ok(e.getMessage());
		}


		return ResponseEntity.ok(findActividadInsert);
	}
	
	@PostMapping("/reporte")
	public ResponseEntity<?> getReporteEquipo(@RequestBody Actividad actividad) {
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
