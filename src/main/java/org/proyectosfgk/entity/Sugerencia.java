package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sugerencia database table.
 * 
 */
@Entity
@NamedQuery(name="Sugerencia.findAll", query="SELECT s FROM Sugerencia s")
public class Sugerencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comentario;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public Sugerencia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}