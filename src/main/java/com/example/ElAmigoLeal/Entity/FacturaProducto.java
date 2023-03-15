package com.example.ElAmigoLeal.Entity;

import javax.persistence.CascadeType;
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
@Table(name = "facturasproductos")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class FacturaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "cantidad")
	private Integer cantidad;
	@Column(name = "subtotal")
	private Integer subtotal;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="idfactura")
	private Factura factura;
	
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

    public FacturaProducto() {
		
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

	public Integer getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public FacturaProducto(Integer id, Integer cantidad, Integer subtotal, Factura factura, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.factura = factura;
		this.producto = producto;
	}

	public FacturaProducto(Integer cantidad, Integer subtotal, Factura factura, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.factura = factura;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "FacturaProducto [id=" + id + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", factura="
				+ factura + ", producto=" + producto + "]";
	}

}