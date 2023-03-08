package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.CarroProducto;
import com.example.ElAmigoLeal.Impl.CarroProductoService;
import com.example.ElAmigoLeal.Repository.CarroProductoRepository;


@Service
public class CarroProductoServiceImpl implements CarroProductoService{
	
	@Autowired
	private CarroProductoRepository carroProductoRepository;

	@Override
	public List<CarroProducto> findAll() {
		return  carroProductoRepository.findAll();
	}

	@Override
	public CarroProducto save(CarroProducto carroProducto) {
		return carroProductoRepository.save(carroProducto);
	}

	@Override
	public List<CarroProducto> saveAll(List<CarroProducto> carroProducto) {
		return carroProductoRepository.saveAll(carroProducto);
	}

	@Override
	public CarroProducto findbyId(Integer idcarroproducto) {
		return carroProductoRepository.findById(idcarroproducto).orElse(null);
	}

	@Override
	public void delete(Integer idcarroproducto) {
		carroProductoRepository.deleteById(idcarroproducto);
		
	}

	@Override
	public List<CarroProducto> findByCarroCompra(CarroCompra carroCompra) {
		return carroProductoRepository.findByCarroCompra(carroCompra);
	}

}