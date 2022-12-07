package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.DetalleDomicilio;


public interface DetalleDomicilioService {
	 public List<DetalleDomicilio> listarTodosLosDetalleDomicilios();
		
		public DetalleDomicilio guardarDetalleDomicilio(DetalleDomicilio detalledomicilio);
		
		public DetalleDomicilio obtenerDetalleDomiciliobyId(Integer id);
		
		public DetalleDomicilio actualizarDetalleDomicilio(DetalleDomicilio detalledomicilio);
		
		public void eliminarDetalleDomicilio(Integer id);
}
