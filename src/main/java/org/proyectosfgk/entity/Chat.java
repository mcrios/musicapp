package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the chat database table.
 * 
 */
@Entity
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String titulo;

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="chat")
	private List<Mensaje> mensajes;

	//bi-directional many-to-one association to UsuarioChat
	@OneToMany(mappedBy="chat")
	private List<UsuarioChat> usuarioChats;

	public Chat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setChat(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setChat(null);

		return mensaje;
	}

	public List<UsuarioChat> getUsuarioChats() {
		return this.usuarioChats;
	}

	public void setUsuarioChats(List<UsuarioChat> usuarioChats) {
		this.usuarioChats = usuarioChats;
	}

	public UsuarioChat addUsuarioChat(UsuarioChat usuarioChat) {
		getUsuarioChats().add(usuarioChat);
		usuarioChat.setChat(this);

		return usuarioChat;
	}

	public UsuarioChat removeUsuarioChat(UsuarioChat usuarioChat) {
		getUsuarioChats().remove(usuarioChat);
		usuarioChat.setChat(null);

		return usuarioChat;
	}

}