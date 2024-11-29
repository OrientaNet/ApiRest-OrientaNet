package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.ResultadoTestDTO;

import com.grupo2.orientanet.model.entity.Carrera;

import com.grupo2.orientanet.model.entity.ResultadoTest;

public interface ResultadoTestService {

    ResultadoTestDTO obtenerResultadoPorId(Long id);

    Carrera obtenerCarreraMasRecomendada();

    ResultadoTestDTO obtenerPorUsuarioId(Long estudianteId);
}
