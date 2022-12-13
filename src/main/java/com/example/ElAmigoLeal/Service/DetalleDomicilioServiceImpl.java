package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.DetalleDomicilio;
import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Impl.DetalleDomicilioService;
import com.example.ElAmigoLeal.Repository.DetalleDomicilioRepository;

@Service
public class DetalleDomicilioServiceImpl implements DetalleDomicilioService{

	@Autowired
	private DetalleDomicilioRepository detalledomicilioRepository;

	@Override
	public List<DetalleDomicilio> findAll() {
		return detalledomicilioRepository.findAll();
	}

	@Override
	public DetalleDomicilio save(DetalleDomicilio detalledomicilio) {
		return detalledomicilioRepository.save(detalledomicilio);
	}

	@Override
	public DetalleDomicilio findbyId(Integer id) {
		return detalledomicilioRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		detalledomicilioRepository.deleteById(id);
	}

	@Override
	public List<DetalleDomicilio> findbyDomicilio(Domicilio domicilio) {
		return detalledomicilioRepository.findByDomicilio(domicilio);
	}

	
}
