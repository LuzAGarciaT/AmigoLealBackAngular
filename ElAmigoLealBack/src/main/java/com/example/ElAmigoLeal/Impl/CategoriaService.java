package com.example.ElAmigoLeal.Impl;

import java.util.List;


import com.example.ElAmigoLeal.Entity.Categoria;


public interface CategoriaService {

	public List <Categoria> findAll();
	public Categoria save (Categoria categoria);
	public Categoria findbyId (Integer idcategoria);
	public void delete(Integer idcategoria);
	
}


