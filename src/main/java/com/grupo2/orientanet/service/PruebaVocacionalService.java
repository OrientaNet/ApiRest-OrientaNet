package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.PruebaVocacional;
import com.grupo2.orientanet.model.entity.ResultadoTest;

import java.util.List;
import java.util.Map;

public interface PruebaVocacionalService {

    ResultadoTest realizarPrueba(Long pruebaId, Map<Long, Long> respuestasSeleccionadas);

    PruebaVocacional crearPrueba(PruebaVocacional pruebaVocacional);

    PruebaVocacional obtenerPruebaPorId(Long id);

    List<PruebaVocacional> obtenerTodasLasPruebas();

    PruebaVocacional actualizarPrueba(Long id, PruebaVocacional pruebaVocacional);

    void eliminarPrueba(Long id);
}
