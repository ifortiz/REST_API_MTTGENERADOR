package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.service.IUsuarioService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/usuario")

public class UsuarioController {
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private IUsuarioService service;
	

	
	@GetMapping("/listar")
	public List<Usuario> listar() {
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
		
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			usuario = service.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null){
			response.put("mensaje", "USUARIO NO EXISTE"+id.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> checkUser(@RequestBody Usuario usuario) {
		Usuario oUser=service.findByEmail(usuario.getCorreo());
		if(oUser==null) {
			usuario.setNombre("1");
			usuario.setPassword("email not exits");
			return ResponseEntity.ok(usuario);
		}
		if(!bcrypt.matches(usuario.getPassword(), oUser.getPassword())) {
			usuario.setNombre("2");
			usuario.setPassword("email or password incorrect");
		return ResponseEntity.ok(usuario);
		}
		oUser.setPassword("###");
		
		return ResponseEntity.ok(oUser);
		
		

	}
	
	

	@PostMapping("/usuario")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		
		Usuario newUsuario =  usuario;
		if(usuario.getPassword().length()<7 || usuario.getPassword().length()>15) {
			newUsuario.setNombre("1");
			return  ResponseEntity.ok(newUsuario);
		}else {
			if(usuario.getNombre().isEmpty()|| usuario.getCorreo().isEmpty()) {
				newUsuario.setNombre("2");
				return  ResponseEntity.ok(newUsuario);
				
			}else {
				try {
					newUsuario.setPassword(bcrypt.encode(usuario.getPassword()));
					newUsuario = service.save(newUsuario);
					return  ResponseEntity.ok(newUsuario);
				} catch (DataAccessException e) {
					newUsuario.setNombre("500");
					return  ResponseEntity.ok(newUsuario);
				}
			}
		}
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {

		Usuario currentUsuario = service.findById(id);
		Usuario usuarioUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (currentUsuario == null) {
			response.put("mensaje","ERROR EN BASE");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentUsuario.setNombre(usuario.getNombre());
			currentUsuario.setApellido(usuario.getApellido());
			currentUsuario.setCorreo(usuario.getCorreo());
			usuarioUpdated = service.save(currentUsuario);

		} catch (DataAccessException e) {
			response.put("mensaje", "ERROR EN BASE");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "ACTUALIZADO CORRECTAMENTE");
		response.put("usuario", usuarioUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuario/{id}")
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
