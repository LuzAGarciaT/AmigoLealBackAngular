package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Categoria;

import com.example.ElAmigoLeal.Impl.CategoriaService;
import com.example.ElAmigoLeal.Repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> findAll() {
		return  categoriaRepository.findAll();
	}

	@Override
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria  findbyId(Integer idcategoria) {
		return categoriaRepository.findById(idcategoria).orElse(null);
	}

	@Override
	public void delete(Integer idcategoria) {
		categoriaRepository.deleteById(idcategoria);
	}

}


