package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}
