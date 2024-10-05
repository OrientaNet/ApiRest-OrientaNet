package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.ResultadoTestDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.ResultadoTestMapper;
import com.grupo2.orientanet.model.entity.Carrera;

import com.grupo2.orientanet.model.entity.ResultadoTest;
import com.grupo2.orientanet.repository.ResultadoTestRepository;
import com.grupo2.orientanet.service.ResultadoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultadoTestServiceImpl implements ResultadoTestService {

    @Autowired
    private ResultadoTestRepository resultadoTestRepository;
    @Autowired
    private ResultadoTestMapper resultadoTestMapper;

    @Transactional(readOnly = true)
    public ResultadoTestDTO obtenerResultadoPorId(Long id) {
        ResultadoTest resultadoTest = resultadoTestRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Resultado no encontrado"));
        return resultadoTestMapper.toDTO(resultadoTest);
    }

    public Carrera obtenerCarreraMasRecomendada() {
        List<ResultadoTest> resultados = resultadoTestRepository.findAll();

        // Contar las recomendaciones por carrera
        Map<Carrera, Long> conteoCarreras = resultados.stream()
                .collect(Collectors.groupingBy(ResultadoTest::getCarrera, Collectors.counting()));

        // Obtener la carrera con el conteo más alto
        return conteoCarreras.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null); // Retornar null si no hay recomendaciones

    @Transactional(readOnly = true)
    public ResultadoTestDTO obtenerResultadoPorId(Long id) {
        ResultadoTest resultadoTest = resultadoTestRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Resultado no encontrado"));
        return resultadoTestMapper.toDTO(resultadoTest);

    }
}
