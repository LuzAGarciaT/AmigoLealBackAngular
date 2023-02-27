package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.FacturaProducto;
import com.example.ElAmigoLeal.Impl.FacturaProductoService;
import com.example.ElAmigoLeal.Repository.FacturaProductoRepository;

@Service
public class FacturaProductoServiceImpl implements FacturaProductoService{

	@Autowired
	private FacturaProductoRepository facturaproductoRepository;
	 
	@Override
	public List<FacturaProducto> findAll() {
		return  facturaproductoRepository.findAll();
	}

	@Override
	public FacturaProducto save(FacturaProducto facturaproducto) {
		return facturaproductoRepository.save(facturaproducto);
	}
	
	@Override
	public List<FacturaProducto> saveAll(List<FacturaProducto> facturaProducto) {
		return facturaproductoRepository.saveAll(facturaProducto);
	}

	@Override
	public FacturaProducto findbyId(Integer id) {
		return facturaproductoRepository.findById(id).orElse(null);	
	}

	@Override
	public void delete(Integer id) {
		facturaproductoRepository.deleteById(id);	
	}
	
}