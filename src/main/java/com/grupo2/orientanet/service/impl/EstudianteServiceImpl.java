package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.EstudianteDTO;
import com.grupo2.orientanet.mapper.EstudianteMapper;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.model.entity.Experto;
import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.repository.EstudianteRepository;
import com.grupo2.orientanet.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstudianteDTO> findAll() {
        List<Estudiante> estudiante = estudianteRepository.findAll();
        return estudiante.stream().map(estudianteMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EstudianteDTO findById(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Estudiante no encontrado"));
        return estudianteMapper.toDTO(estudiante);
    }

    @Transactional
    @Override
    public EstudianteDTO save(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        estudiante = estudianteRepository.save(estudiante);

        return estudianteMapper.toDTO(estudiante);
    }

    @Transactional
    @Override
    public EstudianteDTO update(Long id, EstudianteDTO estudianteDetails) throws Exception {

        if (!estudianteRepository.existsById(id)) {
            throw new Exception("El estudiante no existe");
        }

        Estudiante existingEstudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new Exception("El estudiante con el id "+id+" no existe"));

                    existingEstudiante.setInformacionPersonal(estudianteDetails.getInformacionPersonal());
                    existingEstudiante.setNivelAcademico(estudianteDetails.getNivelAcademico());
                    existingEstudiante.setInstitucion(estudianteDetails.getInstitucion());
                    existingEstudiante.setIntereses(estudianteDetails.getIntereses());

                    Carrera carrera = new Carrera();
                    carrera.setId(estudianteDetails.getCarreraInteres());
                    existingEstudiante.setCarreraInteres(carrera);

                    Usuario usuario = new Usuario();
                    usuario.setId(estudianteDetails.getUsuarioId());
                    existingEstudiante.setUsuario(usuario);

                    existingEstudiante = estudianteRepository.save(existingEstudiante);
                    return estudianteMapper.toDTO(existingEstudiante);

    }

    @Transactional
    @Override
    public void delete(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El id del estudiante no fue encontrado"));
        estudianteRepository.delete(estudiante);
    }

}

