package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.CarroCompraService;
import com.example.ElAmigoLeal.Repository.CarroCompraRepository;

@Service
public class CarroCompraServiceImpl implements CarroCompraService {

	@Autowired
	private CarroCompraRepository carrocompraRepository;

	@Override
	public List<CarroCompra> findAll() {
		return  carrocompraRepository.findAll();
	}

	@Override
	public CarroCompra save(CarroCompra carrocompra) {
		return carrocompraRepository.save(carrocompra);
	}

	@Override
	public CarroCompra findbyId(Integer idcarro) {
		return carrocompraRepository.findById(idcarro).orElse(null);

	}

	@Override
	public void delete(Integer idcarro) {
		carrocompraRepository.deleteById(idcarro);
	}

	@Override
	public CarroCompra findByUsuarioAndEstado(Usuario usuario, String estado) {
		return carrocompraRepository.findByUsuarioAndEstado(usuario, estado);
	}

	

}