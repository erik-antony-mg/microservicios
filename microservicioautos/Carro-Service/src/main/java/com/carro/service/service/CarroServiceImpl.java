package com.carro.service.service;

import com.carro.service.entity.Carro;
import com.carro.service.repository.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CarroServiceImpl implements CarroService{

    private CarroRepository carroRepository;

    @Override
    public List<Carro> obtenerCarros() {
        return carroRepository.findAll();
    }
    @Override
    public Carro obtenerCarroPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(()->new RuntimeException());
    }
    @Override
    public Carro crearCarro(Carro carro) {
        return carroRepository.save(carro);
    }
    @Override
    public List<Carro> obtenerCarroPorUsuarioId(Long usuarioId) {
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
