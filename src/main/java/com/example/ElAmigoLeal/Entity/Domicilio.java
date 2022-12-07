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
@Table(name = "domicilios")
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddomicilio;
	@Column(name = "descripcion", length = 45)
	private String descripcion;

	@OneToMany (targetEntity = DetalleDomicilio.class, cascade=CascadeType.ALL)
	@JoinColumn (name="id", referencedColumnName = "iddomicilio")
		
	private List<DetalleDomicilio> detalledomicilio;
	
	@ManyToOne
	@JoinColumn(name="idcarro")
	private CarroCompra carrocompra;
	
	public Domicilio() {
		
	}

	public Domicilio(Integer iddomicilio, String descripcion) {
		super();
		this.iddomicilio = iddomicilio;
		this.descripcion = descripcion;
	}

	public Domicilio( String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Integer getIddomicilio() {
		return iddomicilio;
	}

	public void setIddomicilio(Integer iddomicilio) {
		this.iddomicilio = iddomicilio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DetalleDomicilio> getDetalledomicilio() {
		return detalledomicilio;
	}

	public void setDetalledomicilio(List<DetalleDomicilio> detalledomicilio) {
		this.detalledomicilio = detalledomicilio;
	}

	public CarroCompra getCarrocompra() {
		return carrocompra;
	}

	public void setCarrocompra(CarroCompra carrocompra) {
		this.carrocompra = carrocompra;
	}

	@Override
	public String toString() {
		return "Domicilio [iddomicilio=" + iddomicilio + ", descripcion=" + descripcion + ", detalledomicilio="
				+ detalledomicilio + ", carrocompra=" + carrocompra + "]";
	}

	
	
}
