package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.DetalleDomicilio;
import com.example.ElAmigoLeal.Entity.Domicilio;



public interface DetalleDomicilioService {
	
	 	public List<DetalleDomicilio> findAll();
		public DetalleDomicilio save(DetalleDomicilio detalledomicilio);
		public DetalleDomicilio findbyId(Integer id);
		public void delete(Integer id);
		public List<DetalleDomicilio> findbyDomicilio(Domicilio domicilio);
}
