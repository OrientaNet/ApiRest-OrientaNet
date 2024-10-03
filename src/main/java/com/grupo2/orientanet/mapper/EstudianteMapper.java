package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.EstudianteDTO;
import com.grupo2.orientanet.dto.ExpertoDTO;
import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.model.entity.Experto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Estudiante toEntity (EstudianteDTO estudianteDTO){

        return modelMapper.map(estudianteDTO, Estudiante.class);

    }

    public EstudianteDTO toDTO (Estudiante estudiante){

        return modelMapper.map(estudiante, EstudianteDTO.class);

    }
}
