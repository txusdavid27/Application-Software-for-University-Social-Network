package core.modelo;


import core.enums.Tipo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase Core Usuario donde se identifica cada atrubuto del Objeto principal y se inicializan.
 */
public class Usuario {
	private String correo;
	private String password;
	private String username;
	private Long telefono;
	private String carrera;
	private String estado;
	private String descripcion;
	private List<Usuario> amigos= new ArrayList<>();
	private List<Chat> chats= new ArrayList<>();
	private UUID id;
	private Tipo tipo;
	private String medio;

	public Usuario(){}
	public Usuario(String correo, String username)
	{
		this.correo = correo;
		this.username = username;
		this.id = UUID.randomUUID();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username=username;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(Usuario amigo) {
		amigos.add(amigo);
	}

	public Long getTelefono() {
		return this.telefono;
	}

	public void setTelefono(long tel) {
		this.telefono=tel;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Usuario(String correo, String username, String pass, Long telefono , String carrera, String estado, String descripcion, UUID ID, Tipo tipo, String medio){
		this.correo = correo;
		this.username = username;
		this.password=pass;
		this.telefono = telefono;
		this.carrera = carrera;
		this.estado= estado;
		this.descripcion= descripcion;
		this.tipo=tipo;
		this.medio= medio;
		this.id = ID;
		/*
		try {
			FileWriter file = new FileWriter("archivos/"+username+".csv");
			file.write("AMIGOS");
		} catch (IOException e) {
			System.out.println("No se pudo crear el archivo");
		}
		 */
	}



	public Usuario(String correo, String username, Long telefono , String carrera, UUID ID)
	{
		this.correo = correo;
		this.username = username;
		this.telefono = telefono;
		this.carrera = carrera;
		this.id = ID;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getMedio() {
		return medio;
	}

	public void setMedio(String medio) {
		this.medio = medio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Chat> getChats() {
		return chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	public Chat buscarChat(String u){
		System.out.println("BUSCANDO");
		for(Chat chat:chats)
		{
			System.out.println("Buscando Chat");
			if(chat.getEmisor().equals(u))
			{
				return chat;
			}
		}
		return null;
	}

}
