package org.proyectosfgk.repository;

import org.proyectosfgk.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	@Query("SELECT u FROM Usuario u WHERE u.clave= :clave AND u.correo = :correo")
	Usuario findByCorreoAndClave(@Param("clave") String clave, @Param("correo")String correo);
}
