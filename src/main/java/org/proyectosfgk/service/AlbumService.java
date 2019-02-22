package org.proyectosfgk.service;

import java.util.List;

import org.proyectosfgk.entity.Album;
import org.proyectosfgk.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository dao;
	
	public List<Album> verTodos(){
		return (List<Album>) dao.findAll();
	}
	
	public void editarGuardar(Album album) {
		dao.save(album);
	}
	
	public void eliminar(Album album) {
		dao.delete(album);
	}
	
	public Album encontrarAlbum(Integer id) {
		return dao.findOne(id);
	}
}
