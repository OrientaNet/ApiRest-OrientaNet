package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Pago toEntity (PagoDTO pagoDTO){

        return modelMapper.map(pagoDTO, Pago.class);

    }

    public PagoDTO toDTO (Pago pago){

        return modelMapper.map(pago, PagoDTO.class);

    }
}
