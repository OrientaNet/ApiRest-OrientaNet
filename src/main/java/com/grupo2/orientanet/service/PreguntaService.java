package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.PreguntaDTO;
import com.grupo2.orientanet.model.entity.Pregunta;

import java.util.List;

public interface PreguntaService {

    PreguntaDTO crearPregunta(PreguntaDTO preguntaDTO) ;
    List<PreguntaDTO> listarPreguntas();
    PreguntaDTO obtenerPreguntaPorId(Long id);
    PreguntaDTO actualizarPregunta(Long id, PreguntaDTO preguntaDTO) throws Exception;
    void eliminarPregunta(Long id);
}
