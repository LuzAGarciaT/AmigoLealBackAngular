package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Entity.Producto;

public interface ProductoService {

	public List<Producto> findAll();
	public Producto save(Producto producto);
	public Producto finallById(Integer idproducto);	
	public void delete(Integer idproducto);
	public List<Producto> findByCategoria(Categoria categoria);
	public List<Producto> findByDescuento(Descuento descuento);
}