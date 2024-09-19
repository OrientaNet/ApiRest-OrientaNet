package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.ResultadoTest;
import com.grupo2.orientanet.service.ResultadoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resultadotest")
public class ResultadoTestController  {

    @Autowired
    private ResultadoTestService resultadoTestService;

    // Método para obtener un resultado de test por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoTest> obtenerResultadoTest(@PathVariable Long id) {
        ResultadoTest resultadoTest = resultadoTestService.obtenerResultadoPorId(id);

        if (resultadoTest == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultadoTest);
    }
}
