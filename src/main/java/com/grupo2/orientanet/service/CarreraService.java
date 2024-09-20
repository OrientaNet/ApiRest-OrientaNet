package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Carrera;

import java.util.List;

public interface CarreraService {

    Carrera crearCarrera(Carrera carrera);

    Carrera obtenerCarreraPorId(Long id);

    List<Carrera> obtenerTodasLasCarreras();

    Carrera actualizarCarrera(Long id, Carrera carrera);

    void eliminarCarrera(Long id);

}
