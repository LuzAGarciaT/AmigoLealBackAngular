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
@Table(name = "carropro")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class CarroProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name="idcarro")
	private CarroCompra carroCompra;
	
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public CarroProducto() {
	
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public CarroCompra getCarroCompra() {
		return carroCompra;
	}

	public void setCarroCompra(CarroCompra carroCompra) {
		this.carroCompra = carroCompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public CarroProducto(Integer id, Integer cantidad, CarroCompra carroCompra, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.carroCompra = carroCompra;
		this.producto = producto;
	}

	public CarroProducto(Integer cantidad, CarroCompra carroCompra, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.carroCompra = carroCompra;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "CarroProducto [id=" + id + ", cantidad=" + cantidad + ", carroCompra=" + carroCompra.getIdcarro() + ", producto="
				+ producto.getNombreproducto() + "]";
	}

}