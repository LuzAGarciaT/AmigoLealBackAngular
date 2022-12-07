package com.example.ElAmigoLeal.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.DetalleDomicilio;
@Repository
public interface DetalleDomicilioRepository extends JpaRepository<DetalleDomicilio, Integer> {

}
