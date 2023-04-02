package com.usuario.service.controller;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;
    @GetMapping()
    public ResponseEntity<List<Usuario>> listaUsuarios(){
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id){
        Usuario usuario=usuarioService.buscarUsuarioPorId(id);
        if (usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), HttpStatus.CREATED);
    }

    @CircuitBreaker(name = "carroService", fallbackMethod = "fallbackGetCarro")
    @GetMapping("/{usuarioId}/carros")
    public ResponseEntity<List<Carro>> obtenerCarrosPorUsuario(@PathVariable Long usuarioId){
        List<Carro> carros=usuarioService.obtenerLosCarrosDelUsuario(usuarioId);
        if (carros==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }

    @CircuitBreaker(name = "carroService", fallbackMethod = "fallbackSaveCarro")
    @PostMapping("/{usuarioId}/crear/carro")
    public ResponseEntity<Carro> crearCarro(@PathVariable Long usuarioId,@RequestBody Carro carro){
        Carro nuevoCarro=usuarioService.crearCarro(carro,usuarioId);
        return ResponseEntity.ok(nuevoCarro);
    }

    private   ResponseEntity<List<Carro>> fallbackGetCarro(@PathVariable Long usuarioId,RuntimeException e){
    return new ResponseEntity("el usuario "+usuarioId+" tienen los carros en el taller",HttpStatus.OK);
    }

    private  ResponseEntity<Carro> fallbackSaveCarro(@PathVariable Long usuarioId,@RequestBody Carro carro, RuntimeException e){
        return new ResponseEntity("el usuario "+usuarioId+" tienen los carros en el taller",HttpStatus.OK);
    }
}
