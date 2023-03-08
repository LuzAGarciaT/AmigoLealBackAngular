package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.Usuario;

public interface FacturaService {
	
	public List<Factura> findAll();
	
	public Factura save(Factura factura);
	
	public Factura findbyId(Integer idfactura);
	
	public void delete(Integer idfactura);
	
	public List<Factura> findByUsuario(Usuario usuario);
	
}
