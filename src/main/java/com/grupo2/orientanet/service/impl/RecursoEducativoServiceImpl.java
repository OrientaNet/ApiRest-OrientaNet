package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.RecursoEducativo;
import com.grupo2.orientanet.repository.RecursoEducativoRepository;
import com.grupo2.orientanet.service.RecursoEducativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoEducativoServiceImpl implements RecursoEducativoService {

    @Autowired
    private RecursoEducativoRepository recursoEducativoRepository;

    // Crear o actualizar un recurso educativo
    @Override
    public RecursoEducativo saveOrUpdate(RecursoEducativo recursoEducativo) {
        return recursoEducativoRepository.save(recursoEducativo);
    }

    // Obtener todos los recursos educativos
    @Override
    public List<RecursoEducativo> getAll() {
        return recursoEducativoRepository.findAll();
    }

    // Obtener un recurso educativo por ID
    public Optional<RecursoEducativo> getById(Long id) {
        return recursoEducativoRepository.findById(id);
    }

    // Eliminar un recurso educativo por ID
    public void deleteById(Long id) {
        recursoEducativoRepository.deleteById(id);
    }
}
