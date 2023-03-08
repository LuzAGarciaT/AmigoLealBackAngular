package com.example.ElAmigoLeal.Impl;

import java.util.List;


import com.example.ElAmigoLeal.Entity.Permiso;

public interface PermisoService {

	public List<Permiso> findAll();
	public Permiso save(Permiso permiso);
	public Permiso findbyId(Integer idpermiso);
	public void delete (Integer idpermiso);
}


