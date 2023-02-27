package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.FacturaProducto;

public interface FacturaProductoService {
	
public List<FacturaProducto> findAll();
	
	public FacturaProducto save(FacturaProducto facturaproducto);
	
	public List <FacturaProducto> saveAll(List <FacturaProducto> facturaProducto);
	
	public FacturaProducto findbyId(Integer id);
	
	public void delete(Integer id);

}