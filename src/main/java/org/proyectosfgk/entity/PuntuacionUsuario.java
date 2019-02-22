package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the puntuacion_usuario database table.
 * 
 */
@Entity
@Table(name="puntuacion_usuario")
@NamedQuery(name="PuntuacionUsuario.findAll", query="SELECT p FROM PuntuacionUsuario p")
public class PuntuacionUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int nota;

	//bi-directional many-to-one association to Cancion
	@ManyToOne
	private Cancion cancion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public PuntuacionUsuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return this.nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Cancion getCancion() {
		return this.cancion;
	}

	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}