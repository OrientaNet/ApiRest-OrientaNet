package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.RespuestaDTO;
import com.grupo2.orientanet.model.entity.Respuesta;

import java.util.List;
import java.util.Optional;

public interface RespuestaService {

    List<RespuestaDTO> listarRespuestas();

    RespuestaDTO obtenerRespuestaPorId(Long id);

    RespuestaDTO guardarRespuesta(RespuestaDTO respuestaDTO);

    void eliminarRespuesta(Long id);
}
