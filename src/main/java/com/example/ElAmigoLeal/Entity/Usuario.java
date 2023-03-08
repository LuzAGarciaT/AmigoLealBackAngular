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
@Table(name = "usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idusuario;
	
	@Column(name = "pnombre", length = 45)
	private String pnombre;
	@Column(name = "snombre", length = 35)
	private String snombre;
	@Column(name = "papellido", length = 35)
	private String papellido;
	@Column(name = "sapellido", length = 35)
	private String sapellido;
	@Column(name = "correo", length = 50)
	private String correo;
	@Column(name = "password", length = 45)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="iddoc")
	private TipoDocumento tipodocumento;
	
	@ManyToOne 
	@JoinColumn(name="idrol")
	private Rol rol;
	
	@JsonIgnore
	@OneToMany(targetEntity = Factura.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idfactura", referencedColumnName = "idusuario")
	private List<Factura> factura;
	
	@JsonIgnore
	@OneToMany(targetEntity = CarroCompra.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "idcarro", referencedColumnName = "idusuario")
	private List<CarroCompra> carrocompra;
	
	public Usuario(){	
	}

	public Usuario(Integer idusuario, String pnombre, String snombre, String papellido, String sapellido, String correo,
			String password) {
		super();
		this.idusuario = idusuario;
		this.pnombre = pnombre;
		this.snombre = snombre;
		this.papellido = papellido;
		this.sapellido = sapellido;
		this.correo = correo;
		this.password = password;
	}
	
	public Usuario(String pnombre, String snombre, String papellido, String sapellido, String correo,
			String password) {
		super();
		this.pnombre = pnombre;
		this.snombre = snombre;
		this.papellido = papellido;
		this.sapellido = sapellido;
		this.correo = correo;
		this.password = password;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getPnombre() {
		return pnombre;
	}

	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}

	public String getSnombre() {
		return snombre;
	}

	public void setSnombre(String snombre) {
		this.snombre = snombre;
	}

	public String getPapellido() {
		return papellido;
	}

	public void setPapellido(String papellido) {
		this.papellido = papellido;
	}

	public String getSapellido() {
		return sapellido;
	}

	public void setSapellido(String sapellido) {
		this.sapellido = sapellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public TipoDocumento getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(TipoDocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", pnombre=" + pnombre + ", snombre=" + snombre + ", papellido="
				+ papellido + ", sapellido=" + sapellido + ", correo=" + correo + ", password=" + password
				+ ", rol=" + rol + ", tipodocumento=" + tipodocumento + "]";
	}

	

}