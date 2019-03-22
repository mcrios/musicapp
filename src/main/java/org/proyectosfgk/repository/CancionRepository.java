package org.proyectosfgk.repository;

import java.util.List;

import org.proyectosfgk.entity.Cancion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends CrudRepository<Cancion, Integer>{

	@Query("SELECT c.archivo FROM Cancion c WHERE id = :id")
	Cancion findCancion (@Param("id") Integer id);
	
	/**
	 * Metodo para encontrar el archivo de la cancion
	 * @return Objecto cancion solo con Archivo
	 */
	@Query("SELECT new Cancion(c.id, c.nombre, c.album, c.genero) FROM Cancion c")
	List<Cancion> findCancionSinArchivo(); 
	
	@Query("SELECT new Cancion(c.id, c.nombre, c.album, c.genero) FROM Cancion c WHERE c.id = :id")
	Cancion findCancionNoAudio(@Param("id")Integer id);
	
	@Query("SELECT new Cancion(c.id, c.nombre, c.album, c.genero) FROM Cancion c WHERE c.album.nombre = :nombreAlbum")
	List<Cancion> findCancionByAlbum(@Param("nombreAlbum")String nombre); 
}
