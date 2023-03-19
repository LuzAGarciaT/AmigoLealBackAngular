

package com.example.ElAmigoLeal.Entity;

import java.sql.Date;
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
@Table(name="descuentos")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Descuento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddescuento;

	@Column(name = "valordescuento")
	private Integer valordescuento;
	
	@Column(name = "fechadescuento", length = 45)
	private Date fechadescuento;
	
	@JsonIgnore
	@OneToMany(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "iddescuento")
	private List<Categoria> categoria;
	
	public Descuento (){
		
	}
	public Descuento(Integer iddescuento, Integer valordescuento, Date fechadescuento){
		super();
		this.iddescuento = iddescuento;
		this.valordescuento = valordescuento;
		this.fechadescuento = fechadescuento;
	}
	
	public Descuento(Integer valordescuento, Date fechadescuento){
		super();
		this.valordescuento = valordescuento;
		this.fechadescuento = fechadescuento;
	}

	public Integer getIddescuento() {
		return iddescuento;
	}

	public void setIddescuento(Integer iddescuento) {
		this.iddescuento = iddescuento;
	}

	public Integer getValordescuento() {
		return valordescuento;
	}

	public void setValordescuento(Integer valordescuento) {
		this.valordescuento = valordescuento;
	}

	public Date getFechadescuento() {
		return fechadescuento;
	}

	public void setFechadescuento(Date fechadescuento) {
		this.fechadescuento = fechadescuento;
	}
	@Override
	public String toString() {
		return "Descuento [iddescuento=" + iddescuento + ", valordescuento=" + valordescuento + ", fechadescuento="
				+ fechadescuento + "]";
	}
	
}
