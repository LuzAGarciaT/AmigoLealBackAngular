package com.example.ElAmigoLeal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Entity.Factura;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer>{
	List<Domicilio> findByFactura(Factura factura);

}
