package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}