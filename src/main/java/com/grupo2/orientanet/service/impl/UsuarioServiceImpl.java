package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.repository.UsuarioRepository;
import com.grupo2.orientanet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}
