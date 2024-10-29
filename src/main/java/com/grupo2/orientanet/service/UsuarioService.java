package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.AuthResponseDTO;
import com.grupo2.orientanet.dto.LoginDTO;
import com.grupo2.orientanet.dto.UsuarioRequestDTO;
import com.grupo2.orientanet.dto.UsuarioResponseDTO;
import com.grupo2.orientanet.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> getAll();
    UsuarioRequestDTO create(UsuarioRequestDTO usuarioRequestDTO) throws Exception;
    UsuarioResponseDTO getById(Long id);
    UsuarioResponseDTO getByEmail(String email);
    UsuarioRequestDTO update(Long id, UsuarioRequestDTO updateUsuarioRequestDTO) throws Exception;
    void delete(Long id);

    AuthResponseDTO login(LoginDTO loginDTO);

}
