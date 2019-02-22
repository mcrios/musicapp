package org.proyectosfgk.service;

import java.util.List;

import org.proyectosfgk.entity.Usuario;
import org.proyectosfgk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository dao;
	
	
	public List<Usuario> verTodos(){
		return (List<Usuario>) dao.findAll();
	}
	
	public Usuario encontrarUsuario(Integer id) {
		return dao.findOne(id);
	}
	
	public Usuario login (Usuario u) {
		return dao.findByCorreoAndClave(u.getClave(), u.getCorreo());
	}
	
	public void crearEditar(Usuario u) {
		dao.save(u);
	}
	
	

}
