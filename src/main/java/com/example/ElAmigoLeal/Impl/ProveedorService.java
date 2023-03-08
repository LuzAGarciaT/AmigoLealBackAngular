package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Proveedor;

public interface ProveedorService {

	public List<Proveedor> findAll();
	public Proveedor save(Proveedor proveedor);
	public Proveedor findbyId(Integer idproveedor);
	public void delete(Integer idproveedor);

}


