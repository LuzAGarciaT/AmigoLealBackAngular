package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Inventario;


public interface InventarioService {
	public List<Inventario> listarTodosLosInventarios();
	
	public Inventario guardarInventario(Inventario inventario);
	
	public Inventario obtenerInventariobyId(Integer idinventario);
	
	public Inventario actualizarInventario(Inventario inventario);
	
	public void eliminarInventario(Integer idinventario);

}
