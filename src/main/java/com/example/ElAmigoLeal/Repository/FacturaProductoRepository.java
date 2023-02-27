package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.FacturaProducto;

@Repository
public interface FacturaProductoRepository extends JpaRepository<FacturaProducto, Integer>{

}