package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Impl.FacturaService;
import com.example.ElAmigoLeal.Repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturarepository;

	@Override
	public List<Factura>  listarTodasLasFacturas() {
		return facturarepository.findAll();
	}

	@Override
	public Factura guardarFactura(Factura factura){
		return facturarepository.save(factura);
	}

	@Override
	public Factura obtenerFacturabyId(Integer idfactura) {
		return facturarepository.findById(idfactura).get();
	}

	@Override
	public Factura actualizarFactura(Factura factura) {
		return facturarepository.save(factura);
	}

	@Override
	public void eliminarFactura(Integer idfactura) {
		facturarepository.deleteById(idfactura);
	}

}
