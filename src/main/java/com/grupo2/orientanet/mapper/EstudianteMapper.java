package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.EstudianteDTO;
import com.grupo2.orientanet.dto.ExpertoDTO;
import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.model.entity.Experto;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        // Configuración específica para mapear Carrera a Long (id)
        modelMapper.typeMap(Estudiante.class, EstudianteDTO.class).addMappings(mapper ->
                mapper.map(src -> src.getCarreraInteres().getId(), EstudianteDTO::setCarreraInteres)
        );

        // Si también necesitas mapear el inverso (de DTO a Entidad), configura de esta manera
        modelMapper.typeMap(EstudianteDTO.class, Estudiante.class).addMappings(mapper ->
                mapper.skip(Estudiante::setCarreraInteres)  // Lo saltamos para que lo manejes manualmente en el servicio si es necesario
        );
    }

    public Estudiante toEntity(EstudianteDTO estudianteDTO) {
        return modelMapper.map(estudianteDTO, Estudiante.class);
    }

    public EstudianteDTO toDTO(Estudiante estudiante) {
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }
}
