package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.ExpertoDTO;

import com.grupo2.orientanet.dto.RecursoEducativoDTO;

import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.ExpertoMapper;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Experto;

import com.grupo2.orientanet.model.entity.RecursoEducativo;

import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.repository.ExpertoRepository;
import com.grupo2.orientanet.repository.RecursoEducativoRepository;
import com.grupo2.orientanet.service.ExpertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertoServiceImpl implements ExpertoService {

    private final ExpertoRepository expertoRepository;
    private final ExpertoMapper expertoMapper;

    private final RecursoEducativoRepository recursoEducativoRepository;

    @Autowired
    public ExpertoServiceImpl(ExpertoRepository expertoRepository, ExpertoMapper expertoMapper, RecursoEducativoRepository recursoEducativoRepository) {
        this.expertoRepository = expertoRepository;
        this.expertoMapper = expertoMapper;
        this.recursoEducativoRepository = recursoEducativoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExpertoDTO> findAll() {
        List<Experto> expertos = expertoRepository.findAll();
        return expertos.stream().map(expertoMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ExpertoDTO findById(Long id) {
        Experto experto = expertoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Experto no encontrado"));
        return expertoMapper.toDTO(experto);
    }

    @Transactional
    @Override
    public ExpertoDTO save(ExpertoDTO expertoDTO) {
        Experto experto = expertoMapper.toEntity(expertoDTO);
        experto = expertoRepository.save(experto);

        return expertoMapper.toDTO(experto);
    }

    @Transactional
    @Override
    public ExpertoDTO update(Long id, ExpertoDTO expertoDetails) throws Exception {

        if (!expertoRepository.existsById(id)) {

            throw new ResourceNotFoundException("El experto no existe");

        }

        Experto existingExperto = expertoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El experto con el id "+id+" no existe"));


                    existingExperto.setInformacionPersonal(expertoDetails.getInformacionPersonal());
                    existingExperto.setExperiencia(expertoDetails.getExperiencia());
                    existingExperto.setCertificaciones(expertoDetails.getCertificaciones());

                    Carrera carrera = new Carrera();
                    carrera.setId(expertoDetails.getCarreraId());
                    existingExperto.setCarrera(carrera);

                    Usuario usuario = new Usuario();
                    usuario.setId(expertoDetails.getUsuarioId());
                    existingExperto.setUsuario(usuario);

        existingExperto = expertoRepository.save(existingExperto);
                    return expertoMapper.toDTO(existingExperto);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Experto experto = expertoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El id del experto no fue encontrado"));
        expertoRepository.delete(experto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RecursoEducativoDTO> obtenerRecursosEducativosPorExperto(Long expertoId) {
        // Usar el repositorio para obtener los recursos educativos del experto directamente
        List<RecursoEducativo> recursosEducativos = recursoEducativoRepository.findByExpertoId(expertoId);

        // Utilizar el mapper para convertir la lista de recursos educativos a DTOs
        return expertoMapper.toRecursoEducativoDTOList(recursosEducativos);

    }
}

