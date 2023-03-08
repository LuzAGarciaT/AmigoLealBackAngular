package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.FacturaService;
import com.example.ElAmigoLeal.Repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Override
	public List<Factura> findAll() {
		return  facturaRepository.findAll();
	}

	@Override
	public Factura save(Factura factura) {
		return facturaRepository.save(factura);
	}

	@Override
	public Factura findbyId(Integer idfactura) {
		return facturaRepository.findById(idfactura).orElse(null);	
	}

	@Override
	public void delete(Integer idfactura) {
		facturaRepository.deleteById(idfactura);
	}

	@Override
	public List<Factura> findByUsuario(Usuario usuario) {
		return facturaRepository.findByUsuario(usuario);
	}

}
