package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);

    Optional<Usuario> obtenerUsuarioPorEmail(String email);

    List<Usuario> obtenerTodosLosUsuarios();
}
