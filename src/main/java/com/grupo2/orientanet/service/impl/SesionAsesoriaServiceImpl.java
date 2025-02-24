package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.SesionAsesoriaDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.SesionAsesoriaMapper;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Experto;
import com.grupo2.orientanet.model.entity.SesionAsesoria;
import com.grupo2.orientanet.repository.CarreraRepository;
import com.grupo2.orientanet.repository.ExpertoRepository;
import com.grupo2.orientanet.repository.SesionAsesoriaRepository;
import com.grupo2.orientanet.service.SesionAsesoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SesionAsesoriaServiceImpl implements SesionAsesoriaService {

    private final SesionAsesoriaRepository sesionAsesoriaRepository;
    private final CarreraRepository carreraRepository;
    private final ExpertoRepository expertoRepository;
    private final SesionAsesoriaMapper sesionAsesoriaMapper;

    @Transactional(readOnly = true)
    @Override
    public List<SesionAsesoriaDTO> findAll() {
        List<SesionAsesoria> sesionesAsesoria = sesionAsesoriaRepository.findAll();
        return sesionesAsesoria.stream().map(sesionAsesoriaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public SesionAsesoriaDTO findById(Long id) {
        SesionAsesoria sesionAsesoria = sesionAsesoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesión de asesoría no encontrada"));
        return sesionAsesoriaMapper.toDTO(sesionAsesoria);
    }

    @Transactional
    @Override
    public SesionAsesoriaDTO save(SesionAsesoriaDTO sesionAsesoriaDTO) {
        // Obtener y asignar Carrera y Experto
        Carrera carrera = carreraRepository.findById(sesionAsesoriaDTO.getCarreraId())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
        Experto experto = expertoRepository.findById(sesionAsesoriaDTO.getExpertoId())
                .orElseThrow(() -> new ResourceNotFoundException("Experto no encontrado"));

        SesionAsesoria sesionAsesoria = sesionAsesoriaMapper.toEntity(sesionAsesoriaDTO);
        sesionAsesoria.setCarrera(carrera);
        sesionAsesoria.setExperto(experto);

        SesionAsesoria savedSesionAsesoria = sesionAsesoriaRepository.save(sesionAsesoria);
        return sesionAsesoriaMapper.toDTO(savedSesionAsesoria);
    }

    @Transactional
    @Override
    public SesionAsesoriaDTO update(Long id, SesionAsesoriaDTO sesionAsesoriaDetails) throws Exception {
        SesionAsesoria existingSesionAsesoria = sesionAsesoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesión de asesoría no encontrada"));

        // Actualizar los campos de la SesionAsesoria
        existingSesionAsesoria.setTema(sesionAsesoriaDetails.getTema());
        existingSesionAsesoria.setUrl(sesionAsesoriaDetails.getUrl());
        existingSesionAsesoria.setFechaInicio(sesionAsesoriaDetails.getFechaInicio());
        existingSesionAsesoria.setFechaFin(sesionAsesoriaDetails.getFechaFin());

        // Actualizar Carrera y Experto
        Carrera carrera = carreraRepository.findById(sesionAsesoriaDetails.getCarreraId())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
        Experto experto = expertoRepository.findById(sesionAsesoriaDetails.getExpertoId())
                .orElseThrow(() -> new ResourceNotFoundException("Experto no encontrado"));

        existingSesionAsesoria.setCarrera(carrera);
        existingSesionAsesoria.setExperto(experto);

        SesionAsesoria updatedSesionAsesoria = sesionAsesoriaRepository.save(existingSesionAsesoria);
        return sesionAsesoriaMapper.toDTO(updatedSesionAsesoria);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        SesionAsesoria sesionAsesoria = sesionAsesoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesión de asesoría no encontrada"));
        sesionAsesoriaRepository.delete(sesionAsesoria);
    }

    @Override
    public List<SesionAsesoriaDTO> findSesionesByExpertoId(Long expertoId) {
        List<SesionAsesoria> sesiones = sesionAsesoriaRepository.findByExpertoId(expertoId);

        if (sesiones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron asesorías para el experto con ID " + expertoId);
        }

        return sesiones.stream()
                .map(sesionAsesoriaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
