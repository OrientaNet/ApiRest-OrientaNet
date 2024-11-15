package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.RespuestaDTO;
import com.grupo2.orientanet.model.entity.Respuesta;
import com.grupo2.orientanet.service.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
@PreAuthorize("hasRole('ADMIN')")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public ResponseEntity<List<RespuestaDTO>> listarRespuestas() {
        List<RespuestaDTO> respuestaDTOS = respuestaService.listarRespuestas();
        return ResponseEntity.ok(respuestaDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> obtenerRespuestaPorId(@PathVariable Long id) {
        RespuestaDTO respuesta = respuestaService.obtenerRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<RespuestaDTO> guardarRespuesta(@Valid @RequestBody RespuestaDTO respuestaDTO) {
        RespuestaDTO respuesta = respuestaService.guardarRespuesta(respuestaDTO);
        return ResponseEntity.ok(respuesta);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
