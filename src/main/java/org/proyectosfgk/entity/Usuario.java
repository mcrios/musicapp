package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.List;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String clave;

	private String correo;

	private Boolean estado;

	private String genero;

	@Lob
	private byte[] imagen;

	private String nombre;

	// bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Mensaje> mensajes;

	// bi-directional many-to-one association to PuntuacionUsuario
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<PuntuacionUsuario> puntuacionUsuarios;

	// bi-directional many-to-one association to Sugerencia
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Sugerencia> sugerencias;

	// bi-directional many-to-one association to UsuarioChat
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<UsuarioChat> usuarioChats;

	@ManyToOne
	private Role role;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setUsuario(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setUsuario(null);

		return mensaje;
	}

	public List<PuntuacionUsuario> getPuntuacionUsuarios() {
		return this.puntuacionUsuarios;
	}

	public void setPuntuacionUsuarios(List<PuntuacionUsuario> puntuacionUsuarios) {
		this.puntuacionUsuarios = puntuacionUsuarios;
	}

	public PuntuacionUsuario addPuntuacionUsuario(PuntuacionUsuario puntuacionUsuario) {
		getPuntuacionUsuarios().add(puntuacionUsuario);
		puntuacionUsuario.setUsuario(this);

		return puntuacionUsuario;
	}

	public PuntuacionUsuario removePuntuacionUsuario(PuntuacionUsuario puntuacionUsuario) {
		getPuntuacionUsuarios().remove(puntuacionUsuario);
		puntuacionUsuario.setUsuario(null);

		return puntuacionUsuario;
	}

	public List<Sugerencia> getSugerencias() {
		return this.sugerencias;
	}

	public void setSugerencias(List<Sugerencia> sugerencias) {
		this.sugerencias = sugerencias;
	}

	public Sugerencia addSugerencia(Sugerencia sugerencia) {
		getSugerencias().add(sugerencia);
		sugerencia.setUsuario(this);

		return sugerencia;
	}

	public Sugerencia removeSugerencia(Sugerencia sugerencia) {
		getSugerencias().remove(sugerencia);
		sugerencia.setUsuario(null);

		return sugerencia;
	}

	public List<UsuarioChat> getUsuarioChats() {
		return this.usuarioChats;
	}

	public void setUsuarioChats(List<UsuarioChat> usuarioChats) {
		this.usuarioChats = usuarioChats;
	}

	public UsuarioChat addUsuarioChat(UsuarioChat usuarioChat) {
		getUsuarioChats().add(usuarioChat);
		usuarioChat.setUsuario(this);

		return usuarioChat;
	}

	public UsuarioChat removeUsuarioChat(UsuarioChat usuarioChat) {
		getUsuarioChats().remove(usuarioChat);
		usuarioChat.setUsuario(null);

		return usuarioChat;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", clave=" + clave + ", correo=" + correo + ", estado=" + estado + ", genero="
				+ genero + ", imagen=" + Arrays.toString(imagen) + ", nombre=" + nombre + ", role=" + role.getAuthority() + "]";
	}
	
	

}