package org.proyectosfgk.service;

import java.util.ArrayList;
import java.util.List;

import org.proyectosfgk.entity.Usuario;
import org.proyectosfgk.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaUserDetailsService")
public class JPAUSerDetails implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioDAO;
	
	private Logger logger = LoggerFactory.getLogger(JPAUSerDetails.class);
	
	@Override
	@Transactional(readOnly =true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioDAO.findByCorreo(username);
		if(user == null) {
        	logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
        
        if(authorities.isEmpty()) {
        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        
		return new User(user.getCorreo(), user.getClave(), user.getEstado(), true, true, true, authorities);
	}

}
