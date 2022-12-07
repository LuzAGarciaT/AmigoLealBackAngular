package com.example.ElAmigoLeal.Impl;		

import java.util.List;


import com.example.ElAmigoLeal.Entity.Rol;

public interface RolService {

	public List <Rol> findAll();
	public Rol save (Rol rol);
	public Rol findbyId (Integer idrol);
	public void delete(Integer idrol);

}
