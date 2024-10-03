package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.PlanDTO;
import com.grupo2.orientanet.dto.SuscripcionDTO;
import com.grupo2.orientanet.model.entity.Plan;
import com.grupo2.orientanet.model.entity.Suscripcion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuscripcionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Suscripcion toEntity (SuscripcionDTO suscripcionDTO){

        return modelMapper.map(suscripcionDTO, Suscripcion.class);

    }

    public SuscripcionDTO toDTO (Suscripcion suscripcion){

        return modelMapper.map(suscripcion, SuscripcionDTO.class);

    }
}
