package com.example.ElAmigoLeal.Entity;

public class Users {
	
	Integer idUsuario;
	String correo;
	String password;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Users(Integer idUsuario, String correo, String password) {
		super();
		this.idUsuario = idUsuario;
		this.correo = correo;
		this.password = password;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Users [idUsuario=" + idUsuario + ", correo=" + correo + ", password=" + password + "]";
	}
	
	
}
