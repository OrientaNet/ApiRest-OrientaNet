package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.Respuesta;
import com.grupo2.orientanet.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public List<Respuesta> listarRespuestas() {
        return respuestaService.listarRespuestas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> obtenerRespuestaPorId(@PathVariable Long id) {
        Optional<Respuesta> respuesta = respuestaService.obtenerRespuestaPorId(id);
        return respuesta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Respuesta guardarRespuesta(@RequestBody Respuesta respuesta) {
        return respuestaService.guardarRespuesta(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> actualizarRespuesta(@PathVariable Long id, @RequestBody Respuesta respuestaActualizada) {
        Optional<Respuesta> respuesta = respuestaService.obtenerRespuestaPorId(id);
        if (respuesta.isPresent()) {
            Respuesta respuestaExistente = respuesta.get();
            respuestaExistente.setDescripcion(respuestaActualizada.getDescripcion());
            respuestaExistente.setValor(respuestaActualizada.getValor());
            respuestaExistente.setPregunta(respuestaActualizada.getPregunta());
            return ResponseEntity.ok(respuestaService.guardarRespuesta(respuestaExistente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
