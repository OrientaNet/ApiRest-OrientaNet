package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.RecursoEducativoDTO;
import com.grupo2.orientanet.model.entity.RecursoEducativo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecursoEducativoMapper {

    private final ModelMapper modelMapper;

    public RecursoEducativo toEntity (RecursoEducativoDTO recursoEducativoDTO){

        RecursoEducativo recursoEducativo = modelMapper.map(recursoEducativoDTO, RecursoEducativo.class);
        recursoEducativoDTO.setRecurso(recursoEducativo.getRecurso().name());
        return modelMapper.map(recursoEducativoDTO, RecursoEducativo.class);

    }

    public RecursoEducativoDTO toDTO (RecursoEducativo recursoEducativo){

        return modelMapper.map(recursoEducativo, RecursoEducativoDTO.class);

    }
}
