package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Proveedor;
import com.example.ElAmigoLeal.Impl.ProveedorService;
import com.example.ElAmigoLeal.Repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Override
	public List<Proveedor> findAll() {
		return  proveedorRepository.findAll();
	}

	@Override
	public Proveedor save(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}

	@Override
	public Proveedor  findbyId(Integer idproveedor) {
		return proveedorRepository.findById(idproveedor).orElse(null);
	}

	@Override
	public void delete(Integer idproveedor) {
		proveedorRepository.deleteById(idproveedor);
	}

}


