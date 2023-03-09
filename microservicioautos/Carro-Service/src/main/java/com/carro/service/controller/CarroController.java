package com.carro.service.controller;

import com.carro.service.entity.Carro;
import com.carro.service.service.CarroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/carro")
public class CarroController {

    private CarroService carroService;
    @GetMapping()
    public ResponseEntity<List<Carro>> obtenerCarro(){
        return ResponseEntity.ok(carroService.obtenerCarros());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCarroPorId(@PathVariable Long id){
        Carro carro=carroService.obtenerCarroPorId(id);
        if (carro==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> obtenerCarrosPorIdUsuario(@PathVariable Long usuarioId){
        List<Carro> carros=carroService.obtenerCarroPorUsuarioId(usuarioId);
        if (carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
    @PostMapping("/crear")
    public ResponseEntity<Carro> crearCarro(@RequestBody Carro carro){
        return new ResponseEntity<>(carroService.crearCarro(carro), HttpStatus.CREATED);
    }

}
