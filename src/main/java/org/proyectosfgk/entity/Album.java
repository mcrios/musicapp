package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String fecha;

	private String nombre;

	@Lob
	private byte[] portada;

	//bi-directional many-to-one association to Artista
	@ManyToOne
	private Artista artista;

	//bi-directional many-to-one association to Cancion
	@OneToMany(mappedBy="album")
	@JsonIgnore
	private List<Cancion> cancions;

	public Album() {
	}
	
	public Album(int id,byte[] portada) {
		this.id = id;
		this.portada = portada;
	}
	
	public Album(int id, String fecha, String nombre, Artista artista) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.nombre = nombre;
		this.artista = artista;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getPortada() {
		return this.portada;
	}

	public void setPortada(byte[] portada) {
		this.portada = portada;
	}

	public Artista getArtista() {
		return this.artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public List<Cancion> getCancions() {
		return this.cancions;
	}

	public void setCancions(List<Cancion> cancions) {
		this.cancions = cancions;
	}

	public Cancion addCancion(Cancion cancion) {
		getCancions().add(cancion);
		cancion.setAlbum(this);

		return cancion;
	}

	public Cancion removeCancion(Cancion cancion) {
		getCancions().remove(cancion);
		cancion.setAlbum(null);

		return cancion;
	}

}