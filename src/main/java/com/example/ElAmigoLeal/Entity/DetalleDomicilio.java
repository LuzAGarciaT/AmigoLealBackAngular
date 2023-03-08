package com.example.ElAmigoLeal.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="detallesdomicilio")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class DetalleDomicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "estado", length = 45)
	private String estado;
	@Column(name = "direccion", length = 45)
	private String direccion;
	@Column(name = "telefono")
	private String telefono;
	
	@ManyToOne
	@JoinColumn (name="iddomicilio")

	private Domicilio domicilio;
	
	
	public DetalleDomicilio() {
		
	}


	public DetalleDomicilio(Integer id, String estado, String direccion, String telefono, Domicilio domicilio) {
		super();
		this.id = id;
		this.estado = estado;
		this.direccion = direccion;
		this.telefono = telefono;
		this.domicilio = domicilio;
	}

	
	public DetalleDomicilio( String estado, String direccion, String telefono, Domicilio domicilio) {
		super();
		this.estado = estado;
		this.direccion = direccion;
		this.telefono = telefono;
		this.domicilio = domicilio;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	@Override
	public String toString() {
		return "DetalleDomicilio [id=" + id + ", estado=" + estado + ", direccion=" + direccion + ", telefono="
				+ telefono + ", domicilio=" + domicilio + "]";
	}


	
	

	
}