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

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.CarroProducto;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.CarroCompraService;
import com.example.ElAmigoLeal.Impl.CarroProductoService;
import com.example.ElAmigoLeal.Impl.UsuarioService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class CarroProductoController {

	@Autowired
	private CarroProductoService carroProductoService;
	
	@Autowired
	private CarroCompraService carroCompraService;
	
	@Autowired
	private UsuarioService usuarioService;

	// listar
	@GetMapping("/carroproducto")
	public List<CarroProducto> listar() {
		return carroProductoService.findAll();
	}

	// guardar categoria
	@PostMapping("/carroproducto/guardar")
	public CarroProducto guardar(@RequestBody CarroProducto carroProducto) {
		return carroProductoService.save(carroProducto);
	}

	@GetMapping("/carroproducto/{idcarroproducto}")
	public CarroProducto CarroProducto(@PathVariable Integer idcategoria) {
		return carroProductoService.findbyId(idcategoria);
	}

	// editar categoria
	@PutMapping("/carroproducto/{idcarroproducto}")
	public CarroProducto editar(@RequestBody CarroProducto carroProducto, @PathVariable Integer idcarroproducto) {
		CarroProducto carroProductoActual = carroProductoService.findbyId(idcarroproducto);
		carroProductoActual.setCantidad(carroProducto.getCantidad());

		return carroProductoService.save(carroProductoActual);
	}

	// eliminar categoria
	@DeleteMapping("/carroproducto/eliminar/{idcarroproducto}")
	public void eliminar(@PathVariable Integer idcarroproducto) {
		carroProductoService.delete(idcarroproducto);
	}
	
	@PostMapping("/carroproducto/guardartodos")
	public List <CarroProducto> guardartodos(@RequestBody List <CarroProducto> carroProducto) {
		return carroProductoService.saveAll(carroProducto);
	}
	
	@GetMapping("/carroproducto/usuario/{idusuario}")
	public List<CarroProducto> listarporusuario(@PathVariable Integer idusuario) {
		Usuario usuario = usuarioService.findbyId(idusuario);
		CarroCompra carroCompra = carroCompraService.findByUsuarioAndEstado(usuario, "activo");
		return carroProductoService.findByCarroCompra(carroCompra);
	}

}