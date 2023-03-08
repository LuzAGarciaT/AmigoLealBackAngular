package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Descuento;

public interface DescuentoService {

	public List<Descuento> findAll();
	public Descuento save(Descuento descuento);
	public Descuento findbyId(Integer iddescuento);
	public void delete(Integer iddescuento);

}
