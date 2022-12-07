package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Impl.InventarioService;
import com.example.ElAmigoLeal.Repository.InventarioRepository;

@Service
public class InventarioServiceImpl implements InventarioService {
	@Autowired
	private InventarioRepository inventariorepository;

	@Override
	public List<Inventario> listarTodosLosInventarios() {
		return inventariorepository.findAll();
	}

	@Override
	public Inventario guardarInventario(Inventario inventario) {
		return inventariorepository.save(inventario);
	}

	@Override
	public Inventario obtenerInventariobyId(Integer idinventario) {
		return inventariorepository.findById(idinventario).get();
	}

	@Override
	public Inventario actualizarInventario(Inventario inventario) {
		return inventariorepository.save(inventario);
	}

	@Override
	public void eliminarInventario(Integer idinventario) {
		inventariorepository.deleteById(idinventario);
	}
}
