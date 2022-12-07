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
@Table(name="permisos")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Permiso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpermiso;
	
	@Column(name="descripcion", length= 45)
	private String descripcion;
	
	@JsonIgnore
	@OneToMany(targetEntity = Rol.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idrol", referencedColumnName = "idpermiso")
	private List<Rol> rol;
	
	public Permiso() {
		
	}

	public Permiso(Integer idpermiso, String descripcion) {
		super();
		this.idpermiso = idpermiso;
		this.descripcion = descripcion;
	}
	
	public Permiso(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Integer getIdpermiso() {
		return idpermiso;
	}

	public void setIdpermiso(Integer idpermiso) {
		this.idpermiso = idpermiso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Rol> getRol() {
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Permiso [idpermiso=" + idpermiso + ", descripcion=" + descripcion + "]";
	}

}
