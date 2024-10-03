package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.PruebaVocacionalDTO;
import com.grupo2.orientanet.model.entity.PruebaVocacional;
import com.grupo2.orientanet.model.entity.ResultadoTest;

import java.util.List;
import java.util.Map;

public interface PruebaVocacionalService {

    ResultadoTest realizarPrueba(Long pruebaId,Long estudianteId, Map<Long, Long> respuestasSeleccionadas);

    PruebaVocacionalDTO crearPrueba(PruebaVocacionalDTO pruebaVocacionalDTO);

    PruebaVocacionalDTO obtenerPruebaPorId(Long id);

    List<PruebaVocacionalDTO> obtenerTodasLasPruebas();

    PruebaVocacionalDTO actualizarPrueba(Long id, PruebaVocacionalDTO pruebaVocacionalDTO) throws Exception;

    void eliminarPrueba(Long id);
}
