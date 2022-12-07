package com.example.ElAmigoLeal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer>{

}
