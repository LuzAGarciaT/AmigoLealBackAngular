package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Impl.DescuentoService;
import com.example.ElAmigoLeal.Repository.DescuentoRepository;

@Service
public class DescuentoServiceImpl implements DescuentoService {
	@Autowired
	private DescuentoRepository descuentoRepository;

	@Override
	public List<Descuento> findAll() {
		return descuentoRepository.findAll();
	}

	@Override
	public Descuento save(Descuento descuento) {
		return descuentoRepository.save(descuento);
	}

	@Override
	public Descuento findbyId(Integer iddescuento) {
		return descuentoRepository.findById(iddescuento).orElse(null);
	}

	@Override
	public void delete(Integer iddescuento) {
		descuentoRepository.deleteById(iddescuento);
	}

}
