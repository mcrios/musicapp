package org.proyectosfgk.repository;

import java.util.List;

import org.proyectosfgk.entity.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer>{

	@Query("SELECT new Album(a.id, a.portada) FROM Album a WHERE a.nombre = :album")
	Album findAlbumByName (@Param("album") String album);
	
	@Query("SELECT new Album(a.id, a.fecha, a.nombre, a.artista) FROM Album a ")
	List<Album> findAll();
}
