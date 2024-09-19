package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
    @Autowired
    private PreguntaService preguntaService;

    @GetMapping
    public List<Pregunta> listarPreguntas() {
        return preguntaService.listarPreguntas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> obtenerPreguntaPorId(@PathVariable Long id) {
        Optional<Pregunta> pregunta = preguntaService.obtenerPreguntaPorId(id);
        return pregunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pregunta guardarPregunta(@RequestBody Pregunta pregunta) {
        return preguntaService.guardarPregunta(pregunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pregunta> actualizarPregunta(@PathVariable Long id, @RequestBody Pregunta preguntaActualizada) {
        Optional<Pregunta> pregunta = preguntaService.obtenerPreguntaPorId(id);
        if (pregunta.isPresent()) {
            Pregunta preguntaExistente = pregunta.get();
            preguntaExistente.setEnunciado(preguntaActualizada.getEnunciado());
            preguntaExistente.setPruebaVocacional(preguntaActualizada.getPruebaVocacional());
            return ResponseEntity.ok(preguntaService.guardarPregunta(preguntaExistente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long id) {
        preguntaService.eliminarPregunta(id);
        return ResponseEntity.noContent().build();
    }
}
