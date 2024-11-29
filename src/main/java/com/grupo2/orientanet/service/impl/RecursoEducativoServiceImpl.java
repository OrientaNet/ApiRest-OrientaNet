package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.RecursoEducativoDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.RecursoEducativoMapper;
import com.grupo2.orientanet.model.entity.RecursoEducativo;
import com.grupo2.orientanet.repository.RecursoEducativoRepository;
import com.grupo2.orientanet.service.RecursoEducativoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecursoEducativoServiceImpl implements RecursoEducativoService {

    private final RecursoEducativoRepository recursoEducativoRepository;
    private final RecursoEducativoMapper recursoEducativoMapper;


    @Transactional
    @Override
    public RecursoEducativoDTO save(RecursoEducativoDTO recursoEducativoDTO) {

        RecursoEducativo recursoEducativo = recursoEducativoMapper.toEntity(recursoEducativoDTO);
        recursoEducativo = recursoEducativoRepository.save(recursoEducativo);
        return recursoEducativoMapper.toDTO(recursoEducativo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RecursoEducativoDTO> getAll() {
        List<RecursoEducativo> recursoEducativos = recursoEducativoRepository.findAll();
        return recursoEducativos.stream().map(recursoEducativoMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public RecursoEducativoDTO getById(Long id) {
        RecursoEducativo recursoEducativo = recursoEducativoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso educativo con el "+id+" no encontrado"));
        return recursoEducativoMapper.toDTO(recursoEducativo);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        RecursoEducativo recursoEducativo = recursoEducativoRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("el id del recurso educativo no fue encontrado"));
        recursoEducativoRepository.delete(recursoEducativo);

    }

    public List<RecursoEducativoDTO> recomendarRecursosPorCarrera(Long carreraId) {
        // Buscar recursos educativos por carrera
        List<RecursoEducativo> recursos = recursoEducativoRepository.findByCarreraId(carreraId);

        // Mapear los recursos educativos a DTOs
        return recursos.stream()
                .map(recurso -> new RecursoEducativoDTO(
                        recurso.getId(),
                        recurso.getNombre(),
                        recurso.getDescripcion(),
                        recurso.getRecurso().name(),
                        recurso.getUrl(),
                        recurso.getCarrera().getId(),
                        recurso.getExperto().getId()))
                .collect(Collectors.toList());

    }


}
