package com.example.ElAmigoLeal.Repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	public List<Usuario> findByRol(Rol rol);
	public Optional<Usuario> findByCorreo(String Correo);
	
	public Usuario findBycorreo(String correo);

}
