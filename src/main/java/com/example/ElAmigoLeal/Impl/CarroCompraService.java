package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Usuario;

public interface CarroCompraService {

	public List<CarroCompra> findAll();
	public CarroCompra save(CarroCompra carrocompra);
	public CarroCompra findbyId(Integer idcarro);	
	public void delete(Integer idcarro);
	public CarroCompra findByUsuarioAndEstado(Usuario usuario, String estado);
	
}