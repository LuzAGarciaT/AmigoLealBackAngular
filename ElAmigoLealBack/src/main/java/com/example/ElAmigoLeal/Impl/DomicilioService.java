package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Domicilio;

public interface DomicilioService {
	 public List<Domicilio> listarTodosLosDomicilios();
		
		public Domicilio guardarDomicilio(Domicilio domicilio);
		
		public Domicilio obtenerDomiciliobyId(Integer iddomicilio);
		
		public Domicilio actualizarDomicilio(Domicilio domicilio);
		
		public void eliminarDomicilio(Integer iddomicilio);
}
