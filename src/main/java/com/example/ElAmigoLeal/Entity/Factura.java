package com.example.ElAmigoLeal.Entity;

import java.util.Date;
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
@Table(name = "facturas")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfactura;
	
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "preciofact", length = 45)
	private Integer preciofact;
	
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(targetEntity = FacturaProducto.class, mappedBy = "factura")
	private List<FacturaProducto> facturaProducto;
	
	@JsonIgnore
	@OneToMany(targetEntity = Domicilio.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idfactura", referencedColumnName = "idfactura")
	private List<Domicilio> domicilio;
	
	
	public Factura() {
		
	}


	public Integer getIdfactura() {
		return idfactura;
	}


	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
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


	public List<FacturaProducto> getFacturaProducto() {
		return facturaProducto;
	}


	public void setFacturaProducto(List<FacturaProducto> facturaProducto) {
		this.facturaProducto = facturaProducto;
	}


	public List<Domicilio> getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(List<Domicilio> domicilio) {
		this.domicilio = domicilio;
	}


	public Factura(Integer idfactura, Date fecha, Integer preciofact, Usuario usuario,
			List<FacturaProducto> facturaProducto, List<Domicilio> domicilio) {
		super();
		this.idfactura = idfactura;
		this.fecha = fecha;
		this.preciofact = preciofact;
		this.usuario = usuario;
		this.facturaProducto = facturaProducto;
		this.domicilio = domicilio;
	}


	public Factura(Date fecha, Integer preciofact, Usuario usuario, List<FacturaProducto> facturaProducto,
			List<Domicilio> domicilio) {
		super();
		this.fecha = fecha;
		this.preciofact = preciofact;
		this.usuario = usuario;
		this.facturaProducto = facturaProducto;
		this.domicilio = domicilio;
	}


	@Override
	public String toString() {
		return "Factura [idfactura=" + idfactura + ", fecha=" + fecha + ", preciofact=" + preciofact + ", usuario="
				+ usuario + ", facturaProducto=" + facturaProducto + ", domicilio=" + domicilio + "]";
	}

}
