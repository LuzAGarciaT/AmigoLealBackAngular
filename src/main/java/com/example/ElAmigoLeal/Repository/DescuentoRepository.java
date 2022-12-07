package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Integer>{

}
