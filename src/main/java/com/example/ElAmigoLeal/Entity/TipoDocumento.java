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
@Table(name="tipodocumentos")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TipoDocumento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddoc;
	
	@Column(name = "tipodoc", length = 45)
	private String tipodoc;
	
	@JsonIgnore
	@OneToMany(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idusuario", referencedColumnName = "iddoc")
	private List<Usuario> usuario;
	
	public TipoDocumento(){
	}
	
	public TipoDocumento(Integer iddoc, String tipodoc){
		super();
		this.iddoc = iddoc;
		this.tipodoc = tipodoc;
	}
	
	public TipoDocumento(String tipodoc){
		super();
		this.tipodoc = tipodoc;
	}

	public Integer getIddoc() {
	return iddoc;
	}

	public void setIddoc(Integer iddoc) {
	this.iddoc = iddoc;
	}

	public String getTipodoc() {
	return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
	this.tipodoc = tipodoc;
	}

	@Override
	public String toString() {
		return "TipoDocumento [iddoc=" + iddoc + ", tipodoc=" + tipodoc + "]";
	}

}
