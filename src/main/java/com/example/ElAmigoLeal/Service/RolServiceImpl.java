package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Impl.RolService;
import com.example.ElAmigoLeal.Repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;

	 @Override
	public List<Rol> findAll() {
		return  rolRepository.findAll();
	}

	@Override
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public Rol findbyId(Integer idrol) {
		return rolRepository.findById(idrol).orElse(null);
	}

	@Override
	public void delete(Integer idrol) {
		rolRepository.deleteById(idrol);
	}

	
}
