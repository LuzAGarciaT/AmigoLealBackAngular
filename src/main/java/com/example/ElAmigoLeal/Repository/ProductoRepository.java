package com.example.ElAmigoLeal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	List<Producto> findByCategoria(Categoria categoria);

	List<Producto> findByDescuento(Descuento descuento);

}