package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.repository.PreguntaRepository;
import com.grupo2.orientanet.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public Pregunta crearPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public List<Pregunta> listarPreguntas() {
        return preguntaRepository.findAll();
    }

    @Override
    public Pregunta obtenerPreguntaPorId(Long id) {
        return preguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
    }

    @Override
    public Pregunta actualizarPregunta(Long id, Pregunta pregunta) {
        Pregunta preguntaExistente = obtenerPreguntaPorId(id);
        preguntaExistente.setDescripcion(pregunta.getDescripcion());
        return preguntaRepository.save(preguntaExistente);
    }

    @Override
    public void eliminarPregunta(Long id) {
        Pregunta pregunta = obtenerPreguntaPorId(id);
        preguntaRepository.delete(pregunta);
    }
}
