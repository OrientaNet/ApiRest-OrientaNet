package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.PreguntaDTO;
import com.grupo2.orientanet.dto.PruebaVocacionalDTO;
import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.model.entity.PruebaVocacional;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PruebaVocacionalMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PruebaVocacional toEntity (PruebaVocacionalDTO pruebaVocacionalDTO){

        return modelMapper.map(pruebaVocacionalDTO, PruebaVocacional.class);

    }

    public PruebaVocacionalDTO toDTO (PruebaVocacional pruebaVocacional){

        return modelMapper.map(pruebaVocacional, PruebaVocacionalDTO.class);

    }
}
