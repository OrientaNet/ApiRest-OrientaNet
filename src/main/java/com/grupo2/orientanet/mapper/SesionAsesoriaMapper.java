package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.PlanDTO;
import com.grupo2.orientanet.dto.SesionAsesoriaDTO;
import com.grupo2.orientanet.model.entity.Plan;
import com.grupo2.orientanet.model.entity.SesionAsesoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SesionAsesoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SesionAsesoria toEntity (SesionAsesoriaDTO sesionAsesoriaDTO){

        return modelMapper.map(sesionAsesoriaDTO, SesionAsesoria.class);

    }

    public SesionAsesoriaDTO toDTO (SesionAsesoria sesionAsesoria){

        return modelMapper.map(sesionAsesoria, SesionAsesoriaDTO.class);

    }
}
