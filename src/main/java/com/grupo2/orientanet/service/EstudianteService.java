package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.EstudianteDTO;
import com.grupo2.orientanet.model.entity.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<EstudianteDTO> findAll();
    EstudianteDTO findById(Long id);
    EstudianteDTO save(EstudianteDTO estudianteDTO);
    EstudianteDTO update(Long id, EstudianteDTO estudianteDetails) throws Exception;
    void delete(Long id);

}
