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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facturasproductos")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class FacturaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "subtotal")
	private Double subtotal;

	@Transient
	private String nombreProducto;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idfactura")
	private Factura factura;

	@ManyToOne
	@JoinColumn(name = "idproducto")
	private Producto producto;

	public FacturaProducto() {

	}

	public String getNombreProducto() {
		// Retorna el nombre del producto si está disponible, sino retorna una cadena
		// vacía
		return this.producto != null ? this.producto.getNombreProducto() : "";
	}
	
	public String getUsuario() {
	    // Retorna el nombre de usuario si está disponible, sino retorna una cadena vacía
	    return this.factura != null && this.factura.getUsuario() != null ? 
	    		this.factura.getUsuario().getPnombre() + " " + this.factura.getUsuario().getPapellido(): "";
	}
	
	

	public Integer getPreciofact() {
	    return this.factura.getPreciofact();
	}


	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
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

	public FacturaProducto(Integer iD, Integer cantidad, Double subtotal, Factura factura, Producto producto) {
		super();
		ID = iD;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.factura = factura;
		this.producto = producto;
	}

	public FacturaProducto(Integer cantidad, Double subtotal, Factura factura, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.factura = factura;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "FacturaProducto [ID=" + ID + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", factura="
				+ factura + ", producto=" + producto + "]";
	}

}