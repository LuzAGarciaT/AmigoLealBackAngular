package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Domicilio;

public interface DomicilioService {
		
	public List<Domicilio> findAll();
	public Domicilio save(Domicilio domicilio);		
	public Domicilio findbyId (Integer iddomicilio);
	public void delete(Integer iddomicilio);
	public List<Domicilio> findbyCarrocompra(CarroCompra carrocompra);
}
