package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.DetalleDomicilio;
import com.example.ElAmigoLeal.Impl.DetalleDomicilioService;
import com.example.ElAmigoLeal.Repository.DetalleDomicilioRepository;

@Service
public class DetalleDomicilioServiceImpl implements DetalleDomicilioService{

	@Autowired
	private DetalleDomicilioRepository detalledomiciliorepository;

	@Override
	public List<DetalleDomicilio> listarTodosLosDetalleDomicilios() {
		return detalledomiciliorepository.findAll();
	}

	@Override
	public DetalleDomicilio guardarDetalleDomicilio(DetalleDomicilio detalledomicilio) {
		return detalledomiciliorepository.save(detalledomicilio);
	}

	@Override
	public DetalleDomicilio obtenerDetalleDomiciliobyId(Integer id) {
		return detalledomiciliorepository.findById(id).get();
	}

	@Override
	public DetalleDomicilio actualizarDetalleDomicilio(DetalleDomicilio detalledomicilio) {
		return detalledomiciliorepository.save(detalledomicilio);
	}

	@Override
	public void eliminarDetalleDomicilio(Integer id) {
		detalledomiciliorepository.deleteById(id);
	}


}
