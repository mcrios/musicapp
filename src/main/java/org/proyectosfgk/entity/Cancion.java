package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the cancion database table.
 * 
 */
@Entity
@NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c")
public class Cancion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] archivo;

	private String nombre;

	// bi-directional many-to-one association to Album
	@ManyToOne
	private Album album;

	// bi-directional many-to-one association to Genero
	@ManyToOne
	private Genero genero;

	// bi-directional many-to-one association to PuntuacionUsuario
	@OneToMany(mappedBy = "cancion")
	@JsonIgnore
	private List<PuntuacionUsuario> puntuacionUsuarios;

	public Cancion() {
	}

	public int getId() {
		return this.id;
	}

	public Cancion(int id, String nombre, Album album, Genero genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.album = album;
		this.genero = genero;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<PuntuacionUsuario> getPuntuacionUsuarios() {
		return this.puntuacionUsuarios;
	}

	public void setPuntuacionUsuarios(List<PuntuacionUsuario> puntuacionUsuarios) {
		this.puntuacionUsuarios = puntuacionUsuarios;
	}

	public PuntuacionUsuario addPuntuacionUsuario(PuntuacionUsuario puntuacionUsuario) {
		getPuntuacionUsuarios().add(puntuacionUsuario);
		puntuacionUsuario.setCancion(this);

		return puntuacionUsuario;
	}

	public PuntuacionUsuario removePuntuacionUsuario(PuntuacionUsuario puntuacionUsuario) {
		getPuntuacionUsuarios().remove(puntuacionUsuario);
		puntuacionUsuario.setCancion(null);

		return puntuacionUsuario;
	}

}