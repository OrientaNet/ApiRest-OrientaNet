package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.PruebaVocacional;
import com.grupo2.orientanet.model.entity.ResultadoTest;
import com.grupo2.orientanet.service.PruebaVocacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaVocacionalController {

    @Autowired
    private PruebaVocacionalService pruebaVocacionalService;


    // Crear una nueva prueba vocacional
    @PostMapping
    public ResponseEntity<PruebaVocacional> crearPrueba(@RequestBody PruebaVocacional pruebaVocacional) {
        PruebaVocacional nuevaPrueba = pruebaVocacionalService.crearPrueba(pruebaVocacional);
        return ResponseEntity.ok(nuevaPrueba);
    }

    // Obtener una prueba vocacional por ID
    @GetMapping("/{id}")
    public ResponseEntity<PruebaVocacional> obtenerPrueba(@PathVariable Long id) {
        PruebaVocacional prueba = pruebaVocacionalService.obtenerPruebaPorId(id);
        return ResponseEntity.ok(prueba);
    }

    // Obtener todas las pruebas vocacionales
    @GetMapping
    public ResponseEntity<List<PruebaVocacional>> obtenerTodasLasPruebas() {
        List<PruebaVocacional> pruebas = pruebaVocacionalService.obtenerTodasLasPruebas();
        return ResponseEntity.ok(pruebas);
    }

    // Actualizar una prueba vocacional
    @PutMapping("/{id}")
    public ResponseEntity<PruebaVocacional> actualizarPrueba(
            @PathVariable Long id,
            @RequestBody PruebaVocacional pruebaVocacional) {
        PruebaVocacional pruebaActualizada = pruebaVocacionalService.actualizarPrueba(id, pruebaVocacional);
        return ResponseEntity.ok(pruebaActualizada);
    }

    // Eliminar una prueba vocacional por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrueba(@PathVariable Long id) {
        pruebaVocacionalService.eliminarPrueba(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint para realizar la prueba vocacional
    @PostMapping("/{pruebaId}/realizar")
    public ResponseEntity<ResultadoTest> realizarPrueba(
            @PathVariable Long pruebaId,
            @RequestBody Map<Long, Long> respuestasSeleccionadas) {

        ResultadoTest resultado = pruebaVocacionalService.realizarPrueba(pruebaId, respuestasSeleccionadas);
        return ResponseEntity.ok(resultado);
    }


}
