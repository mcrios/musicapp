package org.proyectosfgk.controller;

import java.util.List;

import org.proyectosfgk.entity.Cancion;
import org.proyectosfgk.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancion")
public class CancionController {

	@Autowired
	private CancionService service;
	
	@GetMapping("/ver")
	public List<Cancion> verTodas(){
		return service.verTodas();
	}
	@GetMapping("/encontrar/{id}")
	public Cancion encontrarCancion(@PathVariable Integer id) {
		return service.encontrarCancion(id);
	}
	
	@PostMapping("/crear")
	public Cancion crear(@RequestBody Cancion c) {
		service.guardarEditar(c);
		return c;
	}
	
	@PutMapping("/editar")
	public Cancion editar (@RequestBody Cancion c) {
		service.guardarEditar(c);
		return service.encontrarCancion(c.getId());
	}
}
