package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Permiso;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Impl.PermisoService;
import com.example.ElAmigoLeal.Repository.PermisoRepository;

@Service
public class PermisoServiceImpl implements PermisoService {

	@Autowired
	private PermisoRepository permisoRepository;

	@Override
	public List<Permiso> findAll() {
		return (List<Permiso>) permisoRepository.findAll();
	}

	@Override
	public Permiso save(Permiso permiso) {
		return permisoRepository.save(permiso);
	}

	@Override
	public Permiso findbyId(Integer idpermiso) {
		return permisoRepository.findById(idpermiso).orElse(null);
	}

	@Override
	public void delete(Integer idpermiso) {
		permisoRepository.deleteById(idpermiso);
	}

	
}


