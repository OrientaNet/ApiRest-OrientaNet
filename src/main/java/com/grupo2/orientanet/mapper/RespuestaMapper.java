package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.dto.RespuestaDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Respuesta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta toEntity (RespuestaDTO respuestaDTO){

        return modelMapper.map(respuestaDTO, Respuesta.class);

    }

    public RespuestaDTO toDTO (Respuesta respuesta){

        return modelMapper.map(respuesta, RespuestaDTO.class);

    }
}
