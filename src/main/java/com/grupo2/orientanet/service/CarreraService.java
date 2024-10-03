package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.model.entity.Carrera;

import java.util.List;

public interface CarreraService {

    CarreraDTO crearCarrera(CarreraDTO carreraDTO);

    CarreraDTO obtenerCarreraPorId(Long id);

    List<CarreraDTO> obtenerTodasLasCarreras();

    CarreraDTO actualizarCarrera(Long id, CarreraDTO carreraDTO) throws Exception;

    void eliminarCarrera(Long id);

}
