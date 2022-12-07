package com.example.ElAmigoLeal.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="roles")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idrol;
	
	@Column(name="tiporol", length= 45)
	private String tiporol;
	
	@JsonIgnore
	@OneToMany(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idusuario", referencedColumnName = "idrol")
	private List<Usuario> usuario;
	
	@JsonIgnore
	@OneToMany(targetEntity = Permiso.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idpermiso", referencedColumnName = "idrol")
	private List<Permiso> permiso;
	
	public Rol(){
	}

	public Rol(Integer idrol, String tiporol) {
		super();
		this.idrol = idrol;
		this.tiporol = tiporol;
	}

	public Rol(String tiporol) {
		super();
		this.tiporol = tiporol;
	}
	
	public  Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	

	public String getTiporol() {
		return tiporol;
	}

	public void setTiporol(String tiporol) {
		this.tiporol = tiporol;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public List<Permiso> getPermiso() {
		return permiso;
	}

	public void setPermiso(List<Permiso> permiso) {
		permiso = permiso;
	}

	@Override
	public String toString() {
		return "Rol [idrol=" + idrol + ", tiporol=" + tiporol + "]";
	}

	
	
}
