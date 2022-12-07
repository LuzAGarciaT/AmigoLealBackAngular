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

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;
	@Column(name = "nombreproducto", length = 45)
	private String nombreproducto;
	@Column(name = "precioproducto", length = 45)
	private String precioproducto;
	@Column(name = "descripcion", length = 45)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="iddescuento")
	private Descuento descuento;
	
	@OneToMany(targetEntity = Inventario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idinventario", referencedColumnName = "idproducto")
	private List<Inventario> inventario;
	
	@OneToMany(targetEntity = CarroCompra.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idcarro", referencedColumnName = "idproducto")
	private List<CarroCompra> carrocompra;
	
	@OneToMany(targetEntity = Proveedor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproveedor", referencedColumnName = "idproducto")
	private List<Proveedor> proveedor;
	
	public Producto() {
		
	}

	public Producto(Integer idproducto, String nombreproducto, String precioproducto, String descripcion) {
		super();
		this.idproducto = idproducto;
		this.nombreproducto = nombreproducto;
		this.precioproducto = precioproducto;
		this.descripcion = descripcion;
	}
	
	public Producto(String nombreproducto, String precioproducto, String descripcion) {
		super();
		this.nombreproducto = nombreproducto;
		this.precioproducto = precioproducto;
		this.descripcion = descripcion;
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

	public String getPrecioproducto() {
		return precioproducto;
	}

	public void setPrecioproducto(String precioproducto) {
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

	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", nombreproducto=" + nombreproducto + ", precioproducto="
				+ precioproducto + ", descripcion=" + descripcion + "]";
	}

}
