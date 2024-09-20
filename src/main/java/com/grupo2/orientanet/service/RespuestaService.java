package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Respuesta;

import java.util.List;
import java.util.Optional;

public interface RespuestaService {

    List<Respuesta> listarRespuestas();

    Optional<Respuesta> obtenerRespuestaPorId(Long id);

    Respuesta guardarRespuesta(Respuesta respuesta);

    void eliminarRespuesta(Long id);
}
