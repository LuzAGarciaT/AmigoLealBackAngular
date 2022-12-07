package com.example.ElAmigoLeal.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.CarroCompra;

@Repository
public interface CarroCompraRepository extends JpaRepository<CarroCompra, Integer>{

}