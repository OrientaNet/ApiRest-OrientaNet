package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.dto.ExpertoDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Experto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpertoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Experto toEntity (ExpertoDTO expertoDTO){

        return modelMapper.map(expertoDTO, Experto.class);

    }

    public ExpertoDTO toDTO (Experto experto){

        return modelMapper.map(experto, ExpertoDTO.class);

    }
}
