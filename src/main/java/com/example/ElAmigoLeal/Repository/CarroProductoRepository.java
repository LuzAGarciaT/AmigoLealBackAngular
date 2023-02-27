package com.example.ElAmigoLeal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.CarroProducto;

@Repository
public interface CarroProductoRepository extends JpaRepository<CarroProducto, Integer>{
	public List<CarroProducto> findByCarroCompra(CarroCompra carroCompra);

}