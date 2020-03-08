package org.proyectosfgk.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.proyectosfgk.entity.Usuario;
import org.proyectosfgk.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	Logger log = Logger.getLogger(UsuarioController.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/ver")
	public List<Usuario> verTodos(){
		return service.verTodos();
	}
	
	@PostMapping("/login")
	public Usuario login (@RequestBody Usuario u) {
		System.out.println("LLEGO");
		return service.login(u);
	}
	
	@PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Usuario crear(@RequestParam Map<String, Object>  user, @RequestParam(required = false) MultipartFile img) {
		Usuario usuario = new Usuario();
		try {
			usuario = new ObjectMapper().readValue(user.get("user").toString(), Usuario.class);
			usuario.setImagen(img.getBytes());
			usuario.setClave(passwordEncoder.encode(usuario.getClave()));
		} catch (IOException e) {
			log.error("Erro al obtener imagen. ", e);
		}
		service.crearEditar(usuario);
		return usuario;
	}
	
	@PutMapping("/editar")
	public Usuario editar(@RequestBody Usuario u) {
		service.crearEditar(u);
		return service.encontrarUsuario(u.getId());
	}
	
	@GetMapping("/encontrar/{id}")
	public Usuario buscar (@PathVariable Integer id) {
		return service.encontrarUsuario(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> body = new HashMap<>();
		if(service.eliminar(id)) {
			body.put("msg", "Usuario eliminado con exito.");
			return new ResponseEntity<>(body, HttpStatus.OK);
		}else {
			body.put("error", "Ocurrio un error eliminando el usuario");
			return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
		}
	}

}
