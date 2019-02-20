package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String genero;

	//bi-directional many-to-one association to Cancion
	@OneToMany(mappedBy="genero")
	@JsonIgnore
	private List<Cancion> cancions;

	public Genero() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Cancion> getCancions() {
		return this.cancions;
	}

	public void setCancions(List<Cancion> cancions) {
		this.cancions = cancions;
	}

	public Cancion addCancion(Cancion cancion) {
		getCancions().add(cancion);
		cancion.setGenero(this);

		return cancion;
	}

	public Cancion removeCancion(Cancion cancion) {
		getCancions().remove(cancion);
		cancion.setGenero(null);

		return cancion;
	}

}