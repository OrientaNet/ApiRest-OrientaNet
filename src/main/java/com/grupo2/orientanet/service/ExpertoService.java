package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.ExpertoDTO;
import com.grupo2.orientanet.model.entity.Experto;

import java.util.List;
import java.util.Optional;

public interface ExpertoService {
    List<ExpertoDTO> findAll();
    ExpertoDTO findById(Long id);
    ExpertoDTO save(ExpertoDTO expertoDTO);
    ExpertoDTO update(Long id, ExpertoDTO expertoDetails) throws Exception;
    void delete(Long id);
}
