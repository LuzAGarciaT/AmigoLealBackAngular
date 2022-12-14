package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Usuario;


public interface InventarioService {
	public List<Inventario> findAll();
	
	public Inventario save(Inventario inventario);
	
	public Inventario findbyId(Integer idinventario);
	
	public void delete(Integer idinventario);
	public List<Inventario> findByProducto(Producto producto);

}
