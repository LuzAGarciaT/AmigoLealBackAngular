package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.InventarioService;
import com.example.ElAmigoLeal.Repository.InventarioRepository;

@Service
public class InventarioServiceImpl implements InventarioService {
	@Autowired
	private InventarioRepository inventarioRepository;

	@Override
	public List<Inventario> findAll() {
		return inventarioRepository.findAll();
	}

	@Override
	public Inventario save(Inventario inventario) {
		return inventarioRepository.save(inventario);
	}

	@Override
	public Inventario findbyId(Integer idinventario) {
		return inventarioRepository.findById(idinventario).orElse(null);
	}

	@Override
	public void delete(Integer idinventario) {
		inventarioRepository.deleteById(idinventario);
	}

	@Override
	public List<Inventario> findByProducto(Producto producto) {
		return inventarioRepository.findByProducto(producto);
	}

}
