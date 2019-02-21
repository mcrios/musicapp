package org.proyectosfgk.service;

import java.util.List;

import org.proyectosfgk.entity.Cancion;
import org.proyectosfgk.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionService {

	@Autowired
	private CancionRepository dao;
	
	public List<Cancion> verTodas(){
		return dao.findCancionSinArchivo();
	}
	
	public Cancion encontrarCancion(Integer id) {
		return dao.findOne(id);
	}
	
	public void guardarEditar(Cancion c) {
		dao.save(c);
	}
	
	public void eliminar(Cancion c) {
		dao.delete(c);
	}
	
	public byte[] verArchivo(Integer id) {
		return dao.findCancion(id).getArchivo();
	}
	
}
