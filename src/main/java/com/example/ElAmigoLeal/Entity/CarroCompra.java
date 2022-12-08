package com.example.ElAmigoLeal.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="carrocompras")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CarroCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcarro;
	
	@Column(name = "precio")
	private int precio;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "cantidadpagar")
	private int cantidadpagar;
	
	@Column(name = "estado", length = 25)
	private String estado;
	

	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany (targetEntity = Domicilio.class, cascade=CascadeType.ALL)
	@JoinColumn (name="iddomicilio", referencedColumnName = "idcarro")
	private List<Domicilio> domicilio;
	
	@JsonIgnore
	@OneToMany(targetEntity = Producto.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "idcarro")
	private List<Producto> producto;
	
	public CarroCompra() {
	}


	public CarroCompra(Integer idcarro, int precio, int cantidad, int cantidadpagar, String estado, Usuario usuario) {
		super();
		this.idcarro = idcarro;
		this.precio = precio;
		this.cantidad = cantidad;
		this.cantidadpagar = cantidadpagar;
		this.estado = estado;
		this.usuario = usuario;
	}

	public CarroCompra( int precio, int cantidad, int cantidadpagar, String estado, Usuario usuario) {
		super();
		this.precio = precio;
		this.cantidad = cantidad;
		this.cantidadpagar = cantidadpagar;
		this.estado = estado;
		this.usuario = usuario;
	}


	public Integer getIdcarro() {
		return idcarro;
	}


	public void setIdcarro(Integer idcarro) {
		this.idcarro = idcarro;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getCantidadpagar() {
		return cantidadpagar;
	}


	public void setCantidadpagar(int cantidadpagar) {
		this.cantidadpagar = cantidadpagar;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "CarroCompra [idcarro=" + idcarro + ", precio=" + precio + ", cantidad=" + cantidad + ", cantidadpagar="
				+ cantidadpagar + ", estado=" + estado + ", usuario=" + usuario + "]";
	}
	

}
