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
@Table(name="categorias")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcategoria;
	
	@Column(name="nombrecategoria", length= 45)
	private String nombrecategoria;
	
	@JsonIgnore
	@OneToMany(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idproducto", referencedColumnName = "idcategoria")
	private List<Categoria> categoria;
	
	
	public Categoria(){
	}

	public Categoria(Integer idcategoria, String nombrecategoria) {
		super();
		this.idcategoria = idcategoria;
		this.nombrecategoria = nombrecategoria;
	}

	public Categoria(String nombrecategoria) {
		super();
		this.nombrecategoria = nombrecategoria;
	}
	
	public Integer getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}
	

	public String getNombrecategoria() {
		return nombrecategoria;
	}

	public void setNombrecategoria(String nombrecategoria) {
		this.nombrecategoria = nombrecategoria;
	}

	@Override
	public String toString() {
		return "Categoria [idcategoria=" + idcategoria + ", nombrecategoria=" + nombrecategoria + "]";
	}	
}


