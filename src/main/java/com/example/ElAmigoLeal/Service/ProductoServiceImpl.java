package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Impl.ProductoService;
import com.example.ElAmigoLeal.Repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> findAll() {
		return  productoRepository.findAll();
	}

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto finallById(Integer idproducto) {
		return productoRepository.findById(idproducto).orElse(null);	
	}
	@Override
	public void delete(Integer idproducto) {
		productoRepository.deleteById(idproducto);
	}

	@Override
	public List<Producto> findByCategoria(Categoria categoria) {
			return productoRepository.findByCategoria(categoria);
		}
	

	@Override
	public List<Producto> findByDescuento(Descuento descuento) {
		return productoRepository.findByDescuento(descuento);
	}
	

	

	
}