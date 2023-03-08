package com.example.ElAmigoLeal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.Usuario;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{

	List<Factura> findByUsuario(Usuario usuario);

}
