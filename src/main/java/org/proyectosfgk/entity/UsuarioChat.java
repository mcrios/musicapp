package org.proyectosfgk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_chat database table.
 * 
 */
@Entity
@Table(name="usuario_chat")
@NamedQuery(name="UsuarioChat.findAll", query="SELECT u FROM UsuarioChat u")
public class UsuarioChat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Chat
	@ManyToOne
	private Chat chat;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public UsuarioChat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chat getChat() {
		return this.chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}