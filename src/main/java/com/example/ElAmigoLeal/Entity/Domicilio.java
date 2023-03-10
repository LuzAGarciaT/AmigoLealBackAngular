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
@Table(name = "domicilios")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddomicilio;
	@Column(name = "direccion", length = 45)
	private String direccion;
	@Column(name = "estado", length = 45)
	private String estado;
	
	@ManyToOne 
	@JoinColumn(name="idfactura")
	private Factura factura;
	
	
	public Domicilio() {
		
	}


	public Integer getIddomicilio() {
		return iddomicilio;
	}


	public void setIddomicilio(Integer iddomicilio) {
		this.iddomicilio = iddomicilio;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	public Domicilio(Integer iddomicilio, String direccion, String estado, Factura factura) {
		super();
		this.iddomicilio = iddomicilio;
		this.direccion = direccion;
		this.estado = estado;
		this.factura = factura;
	}


	public Domicilio(String direccion, String estado, Factura factura) {
		super();
		this.direccion = direccion;
		this.estado = estado;
		this.factura = factura;
	}


	@Override
	public String toString() {
		return "Domicilio [iddomicilio=" + iddomicilio + ", direccion=" + direccion + ", estado=" + estado
				+ ", factura=" + factura + "]";
	}
	
	
}
