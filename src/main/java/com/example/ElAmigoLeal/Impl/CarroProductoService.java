package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.CarroProducto;


public interface CarroProductoService {
	
	public List <CarroProducto> findAll();
	public CarroProducto save (CarroProducto carroproducto);
	public List <CarroProducto> saveAll(List <CarroProducto> carroProducto);
	public CarroProducto findbyId (Integer idcarroproducto);
	public void delete(Integer idcarroproducto);
	public List<CarroProducto> findByCarroCompra(CarroCompra carroCompra);
}