package org.proyectosfgk.repository;

import org.proyectosfgk.entity.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer>{

	@Query("SELECT a FROM Album a WHERE a.nombre = :album")
	Album findAlbumByName (@Param("album") String album);
}
