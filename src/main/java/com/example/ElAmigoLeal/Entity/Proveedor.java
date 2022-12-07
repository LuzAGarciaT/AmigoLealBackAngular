
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
@Table(name = "proveedores")
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproveedor;

	@Column(name = "nombre", length = 45)
	private String nombre;

	@Column(name ="telefono" )
	private int telefono;

	@Column(name = "correo", length = 50)
	private String correo;

	@Column(name = "direccion", length = 45)
	private String direccion;
	
	@JsonIgnore
	@OneToMany(targetEntity = Producto.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "idproveedor")
	private List<Producto> producto;

	public Proveedor() {
	}

	public Proveedor(Integer idproveedor, String nombre, int telefono, String correo, String direccion) {
		super();
		this.idproveedor = idproveedor;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}
 
	public Proveedor(String nombre, int telefono, String correo, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}
	
	public Integer getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(Integer idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Proveedores [idproveedor=" + idproveedor + ", nombre=" + nombre + ", telefono=" + telefono + ", correo="
				+ correo + ", direccion=" + direccion + "]";
	}	
	
}

