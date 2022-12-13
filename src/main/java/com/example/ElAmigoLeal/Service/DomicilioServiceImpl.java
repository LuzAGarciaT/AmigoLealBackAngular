package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Impl.DomicilioService;
import com.example.ElAmigoLeal.Repository.DomicilioRepository;


@Service
public class DomicilioServiceImpl implements DomicilioService{

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Override
	public List<Domicilio> findAll() {
		return  domicilioRepository.findAll();
	}

	@Override
	public Domicilio save(Domicilio domicilio) {
		return domicilioRepository.save(domicilio);
	}

	@Override
	public Domicilio findbyId(Integer iddomicilio) {
		return domicilioRepository.findById(iddomicilio).orElse(null);
	}

	@Override
	public void delete(Integer iddomicilio) {
		domicilioRepository.deleteById(iddomicilio);
	}

	@Override
	public List<Domicilio> findbyCarrocompra(CarroCompra carrocompra) {
		return domicilioRepository.findByCarrocompra(carrocompra);

	}

	
}
