package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.dto.ResultadoTestDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.ResultadoTest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultadoTestMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResultadoTest toEntity (ResultadoTestDTO resultadoTestDTO){

        return modelMapper.map(resultadoTestDTO, ResultadoTest.class);

    }

    public ResultadoTestDTO toDTO (ResultadoTest resultadoTest){

        return modelMapper.map(resultadoTest, ResultadoTestDTO.class);

    }
}
