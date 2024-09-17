package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Pregunta;

import java.util.List;
import java.util.Optional;

public interface PreguntaService {
    List<Pregunta> listarPreguntas();

    Optional<Pregunta> obtenerPreguntaPorId(Long id);

    Pregunta guardarPregunta(Pregunta pregunta);

    void eliminarPregunta(Long id);
}
