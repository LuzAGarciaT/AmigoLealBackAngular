package com.example.ElAmigoLeal.Impl;

import java.util.List;


import com.example.ElAmigoLeal.Entity.Producto;

public interface ProductoService {

	public List<Producto> listarTodosLosProductos();
	
	public Producto guardarProducto(Producto producto);
	
	public Producto obtenerProductobyId(Integer idproducto);
	
	public Producto actualizarProducto(Producto producto);
	
	public void eliminarProducto(Integer idproducto);


}