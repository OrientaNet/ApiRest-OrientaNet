package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @PostMapping
    public ResponseEntity<Pregunta> crearPregunta(@RequestBody Pregunta pregunta) {
        return ResponseEntity.ok(preguntaService.crearPregunta(pregunta));
    }

    @GetMapping
    public ResponseEntity<List<Pregunta>> listarPreguntas() {
        return ResponseEntity.ok(preguntaService.listarPreguntas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> obtenerPreguntaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(preguntaService.obtenerPreguntaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pregunta> actualizarPregunta(@PathVariable Long id, @RequestBody Pregunta pregunta) {
        return ResponseEntity.ok(preguntaService.actualizarPregunta(id, pregunta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long id) {
        preguntaService.eliminarPregunta(id);
        return ResponseEntity.noContent().build();
    }
}
