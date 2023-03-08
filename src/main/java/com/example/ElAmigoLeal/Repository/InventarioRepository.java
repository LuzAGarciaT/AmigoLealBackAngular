package com.example.ElAmigoLeal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Entity.Producto;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer>{

	List<Inventario> findByProducto(Producto producto);

}
