package com.example.ElAmigoLeal.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Usuario;

@Repository
public interface CarroCompraRepository extends JpaRepository<CarroCompra, Integer>{

	List<CarroCompra> findByUsuario(Usuario usuario);

	

}