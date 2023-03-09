package com.usuario.service.service;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feingclients.CarroFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;
    private RestTemplate restTemplate;
    private CarroFeignClient carroFeignClient;
    @Override
    public Usuario obtenerUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Carro> obtenerLosCarrosDelUsuario(Long usuarioId) {
        List<Carro> carros=restTemplate
                    .getForObject("http://localhost:8080/api/carro/usuario/"+usuarioId,List.class);
        return carros;
    }

    @Override
    public Carro crearCarro(Carro carro,Long usuarioId) {
        carro.setUsuarioId(usuarioId);
        Carro carroNuevo= carroFeignClient.crearCarro(carro);
        return carroNuevo;
    }
}
