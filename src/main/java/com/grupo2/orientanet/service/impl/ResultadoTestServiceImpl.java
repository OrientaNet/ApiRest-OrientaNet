package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.ResultadoTest;
import com.grupo2.orientanet.repository.ResultadoTestRepository;
import com.grupo2.orientanet.service.ResultadoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultadoTestServiceImpl implements ResultadoTestService {

    @Autowired
    private ResultadoTestRepository resultadoTestRepository;

    // Método para obtener un ResultadoTest por ID
    public ResultadoTest obtenerResultadoPorId(Long id) {
        Optional<ResultadoTest> resultadoTest = resultadoTestRepository.findById(id);
        return resultadoTest.orElse(null);
    }
}
