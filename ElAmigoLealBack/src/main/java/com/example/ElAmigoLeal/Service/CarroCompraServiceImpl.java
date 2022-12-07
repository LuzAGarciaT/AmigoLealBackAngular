package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Impl.CarroCompraService;
import com.example.ElAmigoLeal.Repository.CarroCompraRepository;

@Service
public class CarroCompraServiceImpl implements CarroCompraService {

	@Autowired
	private CarroCompraRepository carrocomprarepository;

	@Override
	public List<CarroCompra> listarTodasLosCarroCompra() {
		return carrocomprarepository.findAll();
	}

	@Override
	public CarroCompra guardarCarroCompra(CarroCompra carrocompra) {
		return carrocomprarepository.save(carrocompra);
	}

	@Override
	public CarroCompra obtenerCarroComprabyId(Integer idcarro) {
		return carrocomprarepository.findById(idcarro).get();
	}

	@Override
	public CarroCompra actualizarCarroCompra(CarroCompra carrocompra) {
		return carrocomprarepository.save(carrocompra);
	}

	@Override
	public void eliminarCarroCompra(Integer idcarro) {
		carrocomprarepository.deleteById(idcarro);
	}

}