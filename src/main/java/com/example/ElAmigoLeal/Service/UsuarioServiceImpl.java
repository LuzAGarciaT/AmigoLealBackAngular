package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.UsuarioService;
import com.example.ElAmigoLeal.Repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findAll() {
		return  usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findbyId(Integer idusuario) {
		return usuarioRepository.findById(idusuario).orElse(null);	
	}

	@Override
	public void delete(Integer idusuario) {
		usuarioRepository.deleteById(idusuario);
	}

	@Override
	public List<Usuario> findByRol(Rol rol) {
		return usuarioRepository.findByRol(rol);
	}
	

	
	
}
