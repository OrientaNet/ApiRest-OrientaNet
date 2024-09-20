package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.repository.EstudianteRepository;
import com.grupo2.orientanet.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante update(Long id, Estudiante estudianteDetails) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setInformacionPersonal(estudianteDetails.getInformacionPersonal());
                    estudiante.setNivelAcademico(estudianteDetails.getNivelAcademico());
                    estudiante.setInstitucion(estudianteDetails.getInstitucion());
                    estudiante.setIntereses(estudianteDetails.getIntereses());
                    estudiante.setCarreraInteres(estudianteDetails.getCarreraInteres());
                    estudiante.setUsuario(estudianteDetails.getUsuario());
                    return estudianteRepository.save(estudiante);
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }

}

