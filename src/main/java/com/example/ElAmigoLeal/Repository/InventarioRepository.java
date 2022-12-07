package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer>{

}
