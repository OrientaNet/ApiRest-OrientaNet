package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.AuthResponseDTO;
import com.grupo2.orientanet.dto.LoginDTO;
import com.grupo2.orientanet.dto.UsuarioRegistrationDTO;
import com.grupo2.orientanet.dto.UsuarioProfileDTO;

public interface UsuarioService {

//    List<UsuarioProfileDTO> getAll();
//    UsuarioRegistrationDTO create(UsuarioRegistrationDTO usuarioRegistrationDTO) throws Exception;
//    UsuarioProfileDTO getById(Long id);
//    UsuarioProfileDTO getByEmail(String email);
//    UsuarioRegistrationDTO update(Long id, UsuarioRegistrationDTO updateUsuarioRegistrationDTO) throws Exception;



    UsuarioProfileDTO registerEstudiante(UsuarioRegistrationDTO usuarioRegistrationDTO);

    UsuarioProfileDTO registerExperto(UsuarioRegistrationDTO usuarioRegistrationDTO);

    AuthResponseDTO login(LoginDTO loginDTO);

    UsuarioProfileDTO updateUsuarioProfile(Long id, UsuarioProfileDTO usuarioProfileDTO);

    UsuarioProfileDTO getUsusarioProfileById(Long id);
}


