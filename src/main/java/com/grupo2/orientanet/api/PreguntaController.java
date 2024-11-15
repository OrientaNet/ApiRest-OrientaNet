package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.EstudianteDTO;
import com.grupo2.orientanet.dto.PreguntaDTO;
import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.service.PreguntaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preguntas")
@PreAuthorize("hasRole('ADMIN')")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @PostMapping
    public ResponseEntity<PreguntaDTO> crearPregunta(@Valid @RequestBody PreguntaDTO preguntaDTO) {
        PreguntaDTO createdPregunta = preguntaService.crearPregunta(preguntaDTO);
        return new ResponseEntity<>(createdPregunta, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PreguntaDTO>> listarPreguntas() {
        List<PreguntaDTO> preguntaDTOS = preguntaService.listarPreguntas();
        return new ResponseEntity<>(preguntaDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaDTO> obtenerPreguntaPorId(@PathVariable Long id) {
        PreguntaDTO preguntaDTO = preguntaService.obtenerPreguntaPorId(id);
        return new ResponseEntity<>(preguntaDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreguntaDTO> actualizarPregunta(@PathVariable Long id,@Valid @RequestBody PreguntaDTO preguntaDTO) throws Exception {
        PreguntaDTO updatedPregunta = preguntaService.actualizarPregunta(id, preguntaDTO);
        return new ResponseEntity<>(updatedPregunta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long id) {
        preguntaService.eliminarPregunta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
