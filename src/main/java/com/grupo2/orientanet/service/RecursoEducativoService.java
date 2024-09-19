package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.RecursoEducativo;

import java.util.List;
import java.util.Optional;

public interface RecursoEducativoService {

    RecursoEducativo saveOrUpdate(RecursoEducativo recursoEducativo);
    List<RecursoEducativo> getAll();
    Optional<RecursoEducativo> getById(Long id);
    void deleteById(Long id);
}
