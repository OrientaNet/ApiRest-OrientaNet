package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.AuthResponseDTO;
import com.grupo2.orientanet.dto.LoginDTO;
import com.grupo2.orientanet.dto.UsuarioRequestDTO;
import com.grupo2.orientanet.dto.UsuarioResponseDTO;
import com.grupo2.orientanet.model.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private  ModelMapper modelMapper;

    public Usuario requestToEntity (UsuarioRequestDTO usuarioRequestDTO){

        return modelMapper.map(usuarioRequestDTO, Usuario.class);

    }

    public UsuarioRequestDTO requestToDTO (Usuario usuario){

        return modelMapper.map(usuario, UsuarioRequestDTO.class);

    }

    public Usuario responseToEntity (UsuarioResponseDTO usuarioResponseDTO){

        return modelMapper.map(usuarioResponseDTO, Usuario.class);

    }

    public UsuarioResponseDTO responseToDTO(Usuario usuario){

        return  modelMapper.map(usuario, UsuarioResponseDTO.class);

    }

    public Usuario toUserEntity (LoginDTO loginDTO){

        return modelMapper.map(loginDTO, Usuario.class);

    }

    public AuthResponseDTO toAuthResponseDTO(Usuario usuario, String token){
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();

        authResponseDTO.setNombre(nombre);
        authResponseDTO.setApellido(apellido);

        authResponseDTO.setRole(usuario.getRole().name());

        return authResponseDTO;
    }

}
