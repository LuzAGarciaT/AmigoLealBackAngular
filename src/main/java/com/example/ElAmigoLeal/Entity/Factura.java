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
@Table(name = "facturas")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfactura;
	@Column(name = "nombre", length = 45)
	private String nombre;
	@Column(name = "fecha")
	private String fecha;
	@Column(name = "preciofact", length = 45)
	private Integer preciofact;
	
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;
	
	public Factura() {
		
	}

	public Factura(Integer idfactura, String nombre, String fecha, Integer preciofact) {
		super();
		this.idfactura = idfactura;
		this.nombre = nombre;
		this.fecha = fecha;
		this.preciofact = preciofact;
	}
	
	public Factura(String nombre, String fecha, Integer preciofact) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.preciofact = preciofact;
	}

	public Integer getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getPreciofact() {
		return preciofact;
	}

	public void setPreciofact(Integer preciofact) {
		this.preciofact = preciofact;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Factura [idfactura=" + idfactura + ", nombre=" + nombre + ", fecha=" + fecha + ", preciofact="
				+ preciofact + ", usuario=" + usuario + "]";
	}

	
}
