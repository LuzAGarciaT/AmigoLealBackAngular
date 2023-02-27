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
@Table(name = "productos")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;
	@Column(name = "nombreproducto", length = 45)
	private String nombreproducto;
	@Column(name = "precioproducto", length = 45)
	private Double precioproducto;
	@Column(name = "descripcion", length = 45)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="iddescuento")
	private Descuento descuento;
	
	@JsonIgnore
	@OneToMany(targetEntity = Inventario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idinventario", referencedColumnName = "idproducto")
	private List<Inventario> inventario;
	
	@JsonIgnore
	@OneToMany(targetEntity = CarroProducto.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private List<CarroProducto> carroProducto;
	
	@JsonIgnore
	@OneToMany(targetEntity = Proveedor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproveedor", referencedColumnName = "idproducto")
	private List<Proveedor> proveedor;
	
	@JsonIgnore
	@OneToMany(targetEntity = FacturaProducto.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private List<FacturaProducto> facturaProducto;

	
	public Producto() {
		
	}


	public Integer getIdproducto() {
		return idproducto;
	}


	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}


	public String getNombreproducto() {
		return nombreproducto;
	}


	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}


	public Double getPrecioproducto() {
		return precioproducto;
	}


	public void setPrecioproducto(Double precioproducto) {
		this.precioproducto = precioproducto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Descuento getDescuento() {
		return descuento;
	}


	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}


	public List<Inventario> getInventario() {
		return inventario;
	}


	public void setInventario(List<Inventario> inventario) {
		this.inventario = inventario;
	}


	public List<CarroProducto> getCarroProducto() {
		return carroProducto;
	}


	public void setCarroProducto(List<CarroProducto> carroProducto) {
		this.carroProducto = carroProducto;
	}


	public List<Proveedor> getProveedor() {
		return proveedor;
	}


	public void setProveedor(List<Proveedor> proveedor) {
		this.proveedor = proveedor;
	}


	public List<FacturaProducto> getFacturaProducto() {
		return facturaProducto;
	}


	public void setFacturaProducto(List<FacturaProducto> facturaProducto) {
		this.facturaProducto = facturaProducto;
	}


	public Producto(Integer idproducto, String nombreproducto, Double precioproducto, String descripcion,
			Categoria categoria, Descuento descuento, List<Inventario> inventario, List<CarroProducto> carroProducto,
			List<Proveedor> proveedor, List<FacturaProducto> facturaProducto) {
		super();
		this.idproducto = idproducto;
		this.nombreproducto = nombreproducto;
		this.precioproducto = precioproducto;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuento = descuento;
		this.inventario = inventario;
		this.carroProducto = carroProducto;
		this.proveedor = proveedor;
		this.facturaProducto = facturaProducto;
	}


	public Producto(String nombreproducto, Double precioproducto, String descripcion, Categoria categoria,
			Descuento descuento, List<Inventario> inventario, List<CarroProducto> carroProducto,
			List<Proveedor> proveedor, List<FacturaProducto> facturaProducto) {
		super();
		this.nombreproducto = nombreproducto;
		this.precioproducto = precioproducto;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuento = descuento;
		this.inventario = inventario;
		this.carroProducto = carroProducto;
		this.proveedor = proveedor;
		this.facturaProducto = facturaProducto;
	}


	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", nombreproducto=" + nombreproducto + ", precioproducto="
				+ precioproducto + ", descripcion=" + descripcion + ", categoria=" + categoria + ", descuento="
				+ descuento + ", inventario=" + inventario + ", carroProducto=" + carroProducto + ", proveedor="
				+ proveedor + ", facturaProducto=" + facturaProducto + "]";
	}

}