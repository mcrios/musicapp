package org.proyectosfgk.controller;

import java.util.List;

import org.proyectosfgk.entity.Usuario;
import org.proyectosfgk.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping("/ver")
	public List<Usuario> verTodos(){
		return service.verTodos();
	}
	
	@PostMapping("/login")
	public Usuario login (@RequestBody Usuario u) {
		//System.out.println(u.getCorreo());
		return service.login(u);
	}
	
	@PostMapping("/crear")
	public Usuario crear(@RequestBody Usuario u) {
		service.crearEditar(u);
		return u;
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

}
