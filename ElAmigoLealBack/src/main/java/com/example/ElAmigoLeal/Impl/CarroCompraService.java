package com.example.ElAmigoLeal.Impl;

import java.util.List;


import com.example.ElAmigoLeal.Entity.CarroCompra;

public interface CarroCompraService {

	public List<CarroCompra> listarTodasLosCarroCompra();
	
	public CarroCompra guardarCarroCompra(CarroCompra carrocompra);
	
	public CarroCompra obtenerCarroComprabyId(Integer idcarro);
	
	public CarroCompra actualizarCarroCompra(CarroCompra carrocompra);
	
	public void eliminarCarroCompra(Integer idcarro);
}