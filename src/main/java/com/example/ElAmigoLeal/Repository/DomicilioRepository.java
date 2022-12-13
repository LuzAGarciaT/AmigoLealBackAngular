package com.example.ElAmigoLeal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer>{

	List<Domicilio> findByCarrocompra(CarroCompra carrocompra);

}
