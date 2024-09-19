package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);
    Estudiante save(Estudiante estudiante);
    Estudiante update(Long id, Estudiante estudianteDetails);
    void deleteById(Long id);

}
