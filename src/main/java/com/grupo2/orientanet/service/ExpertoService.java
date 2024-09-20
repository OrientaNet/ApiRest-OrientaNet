package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Experto;

import java.util.List;
import java.util.Optional;

public interface ExpertoService {
    List<Experto> findAll();
    Optional<Experto> findById(Long id);
    Experto save(Experto experto);
    Experto update(Long id, Experto expertoDetails);
    void deleteById(Long id);
}
