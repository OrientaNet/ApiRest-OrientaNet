package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.dto.UsuarioRequestDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarreraMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Carrera toEntity (CarreraDTO carreraDTO){

        return modelMapper.map(carreraDTO, Carrera.class);

    }

    public CarreraDTO toDTO (Carrera carrera){

        return modelMapper.map(carrera, CarreraDTO.class);


    }
}
