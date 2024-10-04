package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.dto.ExpertoDTO;
import com.grupo2.orientanet.dto.RecursoEducativoDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Experto;
import com.grupo2.orientanet.model.entity.RecursoEducativo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<RecursoEducativoDTO> toRecursoEducativoDTOList(List<RecursoEducativo> recursosEducativos) {
        return recursosEducativos.stream()
                .map(recurso -> modelMapper.map(recurso, RecursoEducativoDTO.class))
                .collect(Collectors.toList());
    }
}
