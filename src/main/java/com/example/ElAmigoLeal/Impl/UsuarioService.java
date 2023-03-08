package com.example.ElAmigoLeal.Impl;

import java.util.List;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();

	public Usuario save(Usuario usuario);

	public Usuario findbyId(Integer idusuario);

	public void delete(Integer idusuario);
	
	public List<Usuario> findByRol(Rol rol);


}
