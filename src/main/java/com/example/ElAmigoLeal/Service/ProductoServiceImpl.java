package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Impl.ProductoService;
import com.example.ElAmigoLeal.Repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productorepository;

	@Override
	public List<Producto> listarTodosLosProductos() {
		return productorepository.findAll();
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		return productorepository.save(producto);
	}

	@Override
	public Producto obtenerProductobyId(Integer idproducto) {
		return productorepository.findById(idproducto).get();
	}

	@Override
	public Producto actualizarProducto(Producto producto) {
		return productorepository.save(producto);
	}

	@Override
	public void eliminarProducto(Integer idproducto) {
		productorepository.deleteById(idproducto);
	}

	
}