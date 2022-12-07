package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.Factura;

public interface FacturaService {
	public List<Factura> listarTodasLasFacturas();
	
	public Factura guardarFactura(Factura factura);
	
	public Factura obtenerFacturabyId(Integer idfactura);
	
	public Factura actualizarFactura(Factura factura);
	
	public void eliminarFactura(Integer idfactura);
}
