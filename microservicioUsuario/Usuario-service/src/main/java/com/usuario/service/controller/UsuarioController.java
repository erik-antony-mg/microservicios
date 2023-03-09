package com.usuario.service.controller;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.service.UsuarioService;
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

    @GetMapping("/{usuarioId}/carros")
    public ResponseEntity<List<Carro>> obtenerCarrosPorUsuario(@PathVariable Long usuarioId){
        List<Carro> carros=usuarioService.obtenerLosCarrosDelUsuario(usuarioId);
        if (carros==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }

    @PostMapping("/{usuarioId}/crear/carro")
    public ResponseEntity<Carro> crearCarro(@PathVariable Long usuarioId,@RequestBody Carro carro){
        Carro nuevoCarro=usuarioService.crearCarro(carro,usuarioId);
        return ResponseEntity.ok(nuevoCarro);
    }

}
