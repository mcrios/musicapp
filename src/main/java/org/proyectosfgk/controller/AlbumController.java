package org.proyectosfgk.controller;

import java.util.List;

import org.proyectosfgk.entity.Album;
import org.proyectosfgk.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private AlbumService service;
	
	@GetMapping("/ver")
	public List<Album> verTodos(){
		return service.verTodos();
	}
	
	@GetMapping("/encontrar/{id}")
	public Album encontrarAlbum (@PathVariable Integer id) {
		return service.encontrarAlbum(id);
	}
	
	@PostMapping("/guardar")
	public Album guardar(@RequestBody Album a) {
		service.editarGuardar(a);
		return a;
	}
	
	@PutMapping("/editar")
	public Album editar(@RequestBody Album a) {
		service.editarGuardar(a);
		return service.encontrarAlbum(a.getId());
	}
}
