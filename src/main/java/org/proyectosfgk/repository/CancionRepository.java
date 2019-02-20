package org.proyectosfgk.repository;

import org.proyectosfgk.entity.Cancion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends CrudRepository<Cancion, Integer>{

	
}
