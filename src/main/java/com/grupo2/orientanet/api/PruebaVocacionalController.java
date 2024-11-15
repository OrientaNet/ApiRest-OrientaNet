package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.PruebaVocacionalDTO;
import com.grupo2.orientanet.model.entity.PruebaVocacional;
import com.grupo2.orientanet.model.entity.ResultadoTest;
import com.grupo2.orientanet.service.PruebaVocacionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pruebas")
@PreAuthorize("hasRole('ADMIN')")
public class PruebaVocacionalController {

    @Autowired
    private PruebaVocacionalService pruebaVocacionalService;


    // Crear una nueva prueba vocacional
    @PostMapping
    public ResponseEntity<PruebaVocacionalDTO> crearPrueba(@Valid @RequestBody PruebaVocacionalDTO pruebaVocacionalDTO) {
        PruebaVocacionalDTO nuevaPrueba = pruebaVocacionalService.crearPrueba(pruebaVocacionalDTO);
        return ResponseEntity.ok(nuevaPrueba);
    }

    // Obtener una prueba vocacional por ID
    @GetMapping("/{id}")
    public ResponseEntity<PruebaVocacionalDTO> obtenerPrueba(@PathVariable Long id) {
        PruebaVocacionalDTO prueba = pruebaVocacionalService.obtenerPruebaPorId(id);
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    // Obtener todas las pruebas vocacionales
    @GetMapping
    public ResponseEntity<List<PruebaVocacionalDTO>> obtenerTodasLasPruebas() {
        List<PruebaVocacionalDTO> pruebas = pruebaVocacionalService.obtenerTodasLasPruebas();
        return new ResponseEntity<>(pruebas, HttpStatus.OK);
    }

    // Actualizar una prueba vocacional
    @PutMapping("/{id}")
    public ResponseEntity<PruebaVocacionalDTO> actualizarPrueba(
            @PathVariable Long id,
            @Valid @RequestBody PruebaVocacionalDTO pruebaVocacionalDTO) throws Exception {
        PruebaVocacionalDTO pruebaActualizada = pruebaVocacionalService.actualizarPrueba(id, pruebaVocacionalDTO);
        return new ResponseEntity<>(pruebaActualizada, HttpStatus.OK);
    }

    // Eliminar una prueba vocacional por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrueba(@PathVariable Long id) {
        pruebaVocacionalService.eliminarPrueba(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Endpoint para realizar la prueba vocacional
    @PostMapping("/{estudianteId}/realizar/{pruebaId}")
    public ResponseEntity<ResultadoTest> realizarPrueba(
            @PathVariable Long pruebaId,
            @PathVariable Long estudianteId,
            @RequestBody Map<Long, Long> respuestasSeleccionadas) {

        ResultadoTest resultado = pruebaVocacionalService.realizarPrueba(pruebaId, estudianteId, respuestasSeleccionadas);
        return ResponseEntity.ok(resultado);
    }


}
