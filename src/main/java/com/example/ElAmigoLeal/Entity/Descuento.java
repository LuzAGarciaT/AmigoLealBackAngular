

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
@Table(name="descuentos")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Descuento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddescuento;

	@Column(name = "valordescuento", length = 45)
	private String valordescuento;
	
	@Column(name = "fechadescuento", length = 45)
	private String fechadescuento;
	
	@JsonIgnore
	@OneToMany(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "iddescuento")
	private List<Categoria> categoria;
	
	public Descuento (){
		
	}
	public Descuento(Integer iddescuento, String valordescuento, String fechadescuento){
		super();
		this.iddescuento = iddescuento;
		this.valordescuento = valordescuento;
		this.fechadescuento = fechadescuento;
	}
	
	public Descuento(String valordescuento, String fechadescuento){
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

	public String getValordescuento() {
		return valordescuento;
	}

	public void setValordescuento(String valordescuento) {
		this.valordescuento = valordescuento;
	}

	public String getFechadescuento() {
		return fechadescuento;
	}

	public void setFechadescuento(String fechadescuento) {
		this.fechadescuento = fechadescuento;
	}
	@Override
	public String toString() {
		return "Descuento [iddescuento=" + iddescuento + ", valordescuento=" + valordescuento + ", fechadescuento="
				+ fechadescuento + "]";
	}
	
}
