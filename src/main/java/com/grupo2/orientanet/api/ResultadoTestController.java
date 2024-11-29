package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.ResultadoTestDTO;

import com.grupo2.orientanet.model.entity.Carrera;

import com.grupo2.orientanet.model.entity.ResultadoTest;
import com.grupo2.orientanet.repository.ResultadoTestRepository;
import com.grupo2.orientanet.service.ResultadoTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resultadotest")
@RequiredArgsConstructor
public class ResultadoTestController  {

    private final ResultadoTestService resultadoTestService;

    // Método para obtener un resultado de test por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoTestDTO> obtenerResultadoTest(@PathVariable Long id) {
        ResultadoTestDTO resultadoTest = resultadoTestService.obtenerResultadoPorId(id);

        return ResponseEntity.ok(resultadoTest);
    }

    @GetMapping("/mayor-carrera-recomendada")
    public ResponseEntity<Carrera> obtenerCarreraMasRecomendada() {
        Carrera carreraMasRecomendada = resultadoTestService.obtenerCarreraMasRecomendada();
        return ResponseEntity.ok(carreraMasRecomendada);
    }

    @GetMapping("buscarPorUsuario/{id}")
    public ResponseEntity<ResultadoTestDTO> obtenerResultadoTestPorUsuarioId(@PathVariable Long id) {
        ResultadoTestDTO resultadoTest = resultadoTestService.obtenerPorUsuarioId(id);
        return ResponseEntity.ok(resultadoTest);
    }
}
