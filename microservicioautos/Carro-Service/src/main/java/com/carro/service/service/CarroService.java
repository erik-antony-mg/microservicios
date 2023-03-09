package com.carro.service.service;


import com.carro.service.entity.Carro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarroService {

    public List<Carro> obtenerCarros();
    public Carro obtenerCarroPorId(Long id);
    public Carro crearCarro(Carro carro);
    public List<Carro> obtenerCarroPorUsuarioId(Long usuarioId);

}
