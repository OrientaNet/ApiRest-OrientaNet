package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Pregunta;

import java.util.List;

public interface PreguntaService {

    Pregunta crearPregunta(Pregunta pregunta) ;
    List<Pregunta> listarPreguntas();
    Pregunta obtenerPreguntaPorId(Long id);
    Pregunta actualizarPregunta(Long id, Pregunta pregunta) throws Exception;
    void eliminarPregunta(Long id);
}
