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
@Table(name="inventarios")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Inventario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idinventario;
	@Column(name = "nombreproducto", length = 45)
	private String nombreproducto;
	@Column(name = "cantidad", length = 45)
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;
	
	public Inventario() {
		
	}
    
	public Inventario(Integer idinventario, String nombreproducto, Integer cantidad, Producto producto) {
		super();
		this.idinventario = idinventario;
		this.nombreproducto = nombreproducto;
		this.cantidad = cantidad;
		this.producto = producto;
	}
    
	public Inventario(String nombreproducto, Integer cantidad, Producto producto) {
		super();
		this.nombreproducto = nombreproducto;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Integer getIdinventario() {
		return idinventario;
	}

	public void setIdinventario(Integer idinventario) {
		this.idinventario = idinventario;
	}

	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Inventario [idinventario=" + idinventario + ", nombreproducto=" + nombreproducto + ", cantidad="
				+ cantidad + ", producto=" + producto + "]";
	}

}
