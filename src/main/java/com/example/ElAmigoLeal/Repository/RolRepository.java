package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
