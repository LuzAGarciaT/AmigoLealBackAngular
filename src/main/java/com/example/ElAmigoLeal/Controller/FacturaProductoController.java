package com.example.ElAmigoLeal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.FacturaProducto;
import com.example.ElAmigoLeal.Impl.FacturaProductoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class FacturaProductoController {

	@Autowired
	private FacturaProductoService facturaproductoService;

	@GetMapping("/facturaproducto")
	public List<FacturaProducto> listar() {
		return facturaproductoService.findAll();
	}

	// guardar factura
	@PostMapping("/facturaproducto/guardar")
	public FacturaProducto guardar(@RequestBody FacturaProducto facturaproducto) {
		return facturaproductoService.save(facturaproducto);
	}

	@GetMapping("/facturaproducto/{id}")
	public FacturaProducto getFacturaProducto(@PathVariable Integer id) {
		return facturaproductoService.findbyId(id);
	}

	// editar factura
	@PutMapping("/facturaproducto/{id}")
	public FacturaProducto editar(@RequestBody FacturaProducto facturaproducto, @PathVariable Integer id) {
		FacturaProducto facturaproductoActual = facturaproductoService.findbyId(id);
		facturaproductoActual.setFactura(facturaproducto.getFactura());
		facturaproductoActual.setProducto(facturaproducto.getProducto());
		facturaproductoActual.setCantidad(facturaproducto.getCantidad());
		facturaproductoActual.setSubtotal(facturaproducto.getSubtotal());
		return facturaproductoService.save(facturaproductoActual);
	}

	// eliminar factura
	@DeleteMapping("/facturaproducto/eliminar/{id}")
	public void eliminar(@PathVariable Integer id) {
		facturaproductoService.delete(id);
	}

	@PostMapping("/facturaproducto/guardartodos")
	public List <FacturaProducto> guardartodos(@RequestBody List <FacturaProducto> facturaProducto) {
		return facturaproductoService.saveAll(facturaProducto);
	}
}