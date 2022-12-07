package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer>{

}
