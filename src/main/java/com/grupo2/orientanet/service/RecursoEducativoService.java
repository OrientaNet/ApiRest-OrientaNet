package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.RecursoEducativoDTO;
import com.grupo2.orientanet.model.entity.RecursoEducativo;

import java.util.List;
import java.util.Optional;

public interface RecursoEducativoService {

    RecursoEducativoDTO save(RecursoEducativoDTO recursoEducativoDTO);
    List<RecursoEducativoDTO> getAll();
    RecursoEducativoDTO getById(Long id);
    void deleteById(Long id);
    List<RecursoEducativoDTO> recomendarRecursosPorCarrera(Long carreraId);
}
