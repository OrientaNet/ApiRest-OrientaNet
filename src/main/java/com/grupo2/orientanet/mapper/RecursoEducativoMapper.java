package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.RecursoEducativoDTO;
import com.grupo2.orientanet.model.entity.RecursoEducativo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecursoEducativoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RecursoEducativo toEntity (RecursoEducativoDTO recursoEducativoDTO){

        return modelMapper.map(recursoEducativoDTO, RecursoEducativo.class);

    }

    public RecursoEducativoDTO toDTO (RecursoEducativo recursoEducativo){

        return modelMapper.map(recursoEducativo, RecursoEducativoDTO.class);

    }
}
