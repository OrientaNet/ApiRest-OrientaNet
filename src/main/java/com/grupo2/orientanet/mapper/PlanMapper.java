package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.dto.PlanDTO;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Plan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Plan toEntity (PlanDTO planDTO){

        return modelMapper.map(planDTO, Plan.class);

    }

    public PlanDTO toDTO (Plan plan){

        return modelMapper.map(plan, PlanDTO.class);

    }
}
