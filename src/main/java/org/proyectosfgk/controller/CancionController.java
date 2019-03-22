package org.proyectosfgk.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.proyectosfgk.entity.Cancion;
import org.proyectosfgk.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody ResponseEntity<Cancion> encontrarCancion(@PathVariable Integer id) {
		return new ResponseEntity<Cancion>(service.encontrarCancion(id), HttpStatus.OK);
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
	
	@GetMapping("/stream/{id}")
	@ResponseBody public ResponseEntity<byte[]> getArchivo(@PathVariable Integer id, HttpServletResponse response){
		ResponseEntity<byte[]> file = null;
		byte[] miarchivo = service.encontrarCancion(id).getArchivo();
		
		response.setStatus(HttpStatus.OK.value());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentLength(miarchivo.length);
		file = new ResponseEntity<byte[]>(miarchivo, headers, HttpStatus.OK );
		return file;
	}
	
	@GetMapping("/verPorAlbum/{album}")
	public List<Cancion> verPorAlbum(@PathVariable String album){
		return service.encontrarPorAlbum(album);
	}
	
}
