package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> getAll();
    Usuario create(Usuario usuario) throws Exception;
    Usuario getById(Long id);
    Usuario getByEmail(String email);
    Usuario update(Long id, Usuario updateUsuario) throws Exception;
    void delete(Long id);

}
