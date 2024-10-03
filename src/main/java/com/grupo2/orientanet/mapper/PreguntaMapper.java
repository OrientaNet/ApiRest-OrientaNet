package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.PreguntaDTO;
import com.grupo2.orientanet.dto.RespuestaDTO;
import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.model.entity.Respuesta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreguntaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Pregunta toEntity (PreguntaDTO preguntaDTO){

        return modelMapper.map(preguntaDTO, Pregunta.class);

    }

    public PreguntaDTO toDTO (Pregunta pregunta){

        return modelMapper.map(pregunta, PreguntaDTO.class);

    }
}
