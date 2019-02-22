package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the artista database table.
 * 
 */
@Entity
@NamedQuery(name="Artista.findAll", query="SELECT a FROM Artista a")
public class Artista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="artista")
	@JsonIgnore
	private List<Album> albums;

	public Artista() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setArtista(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setArtista(null);

		return album;
	}

}