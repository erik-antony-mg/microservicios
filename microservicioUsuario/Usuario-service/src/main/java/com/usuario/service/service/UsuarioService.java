package com.usuario.service.service;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {

    Usuario obtenerUsuario(String email);
    public List<Usuario> obtenerUsuarios();
    public Usuario buscarUsuarioPorId(Long id);
    public Usuario guardarUsuario(Usuario usuario);
    public List<Carro> obtenerLosCarrosDelUsuario(Long idUsuario);
    public Carro crearCarro(Carro carro,Long usuarioId);

}
