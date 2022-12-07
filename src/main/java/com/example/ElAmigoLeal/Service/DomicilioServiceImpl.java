package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Impl.DomicilioService;
import com.example.ElAmigoLeal.Repository.DomicilioRepository;


@Service
public class DomicilioServiceImpl implements DomicilioService{

	@Autowired
	private DomicilioRepository domiciliorepository;

	@Override
	public List<Domicilio> listarTodosLosDomicilios() {
		return domiciliorepository.findAll();
	}

	@Override
	public Domicilio guardarDomicilio(Domicilio domicilio) {
		return domiciliorepository.save(domicilio);
	}

	@Override
	public Domicilio obtenerDomiciliobyId(Integer iddomicilio) {
		return domiciliorepository.findById(iddomicilio).get();
	}

	@Override
	public Domicilio actualizarDomicilio(Domicilio domicilio) {
		return domiciliorepository.save(domicilio);
	}

	@Override
	public void eliminarDomicilio(Integer iddomicilio) {
		domiciliorepository.deleteById(iddomicilio);
	}

	
}
