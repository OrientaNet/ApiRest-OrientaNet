package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.PreguntaDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.PreguntaMapper;
import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.model.entity.PruebaVocacional;
import com.grupo2.orientanet.model.entity.Respuesta;
import com.grupo2.orientanet.repository.EstudianteRepository;
import com.grupo2.orientanet.repository.PreguntaRepository;
import com.grupo2.orientanet.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;
    @Autowired
    private PreguntaMapper preguntaMapper;
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    @Override
    public PreguntaDTO crearPregunta(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = preguntaMapper.toEntity(preguntaDTO);
        pregunta = preguntaRepository.save(pregunta);
        return  preguntaMapper.toDTO(pregunta);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PreguntaDTO> listarPreguntas() {
        List<Pregunta> preguntas = preguntaRepository.findAll();
        return preguntas.stream().map(preguntaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PreguntaDTO obtenerPreguntaPorId(Long id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));
        return preguntaMapper.toDTO(pregunta);
    }

    @Transactional
    @Override
    public PreguntaDTO actualizarPregunta(Long id, PreguntaDTO preguntaDTO) throws Exception {

        if (!preguntaRepository.existsById(id)) {
            throw new Exception("La pregunra no existe");
        }

        Pregunta existingPregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La pregunta con el id "+id+" no existe"));


        existingPregunta.setDescripcion(preguntaDTO.getDescripcion());

        if (preguntaDTO.getPruebaVocacionalId() != null) {
            PruebaVocacional pruebaVocacional = new PruebaVocacional();
            pruebaVocacional.setId(preguntaDTO.getPruebaVocacionalId());
            existingPregunta.setPruebaVocacional(pruebaVocacional);
        }

        if (preguntaDTO.getRespuestaIds() != null && !preguntaDTO.getRespuestaIds().isEmpty()) {
            List<Respuesta> respuestas = new ArrayList<>();
            for (Long respuestaId : preguntaDTO.getRespuestaIds()) {
                Respuesta respuesta = new Respuesta();
                respuesta.setId(respuestaId);
                respuestas.add(respuesta);
            }
            existingPregunta.setRespuestas(respuestas);
        }

        existingPregunta = preguntaRepository.save(existingPregunta);

        return preguntaMapper.toDTO(existingPregunta);
    }

    @Transactional
    @Override
    public void eliminarPregunta(Long id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("El id de la pregunta no fue encontrado"));
                preguntaRepository.delete(pregunta);
    }
}
