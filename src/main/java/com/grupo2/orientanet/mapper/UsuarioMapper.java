package com.grupo2.orientanet.mapper;

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
}
