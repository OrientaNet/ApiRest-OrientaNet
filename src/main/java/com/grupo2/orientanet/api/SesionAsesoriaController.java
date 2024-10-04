package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.SesionAsesoriaDTO;
import com.grupo2.orientanet.service.SesionAsesoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sesiones-asesoria")
public class SesionAsesoriaController {

    private final SesionAsesoriaService sesionAsesoriaService;

    @GetMapping
    public ResponseEntity<List<SesionAsesoriaDTO>> getAllSesionesAsesoria() {
        List<SesionAsesoriaDTO> sesionesAsesoria = sesionAsesoriaService.findAll();
        return new ResponseEntity<>(sesionesAsesoria, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionAsesoriaDTO> getSesionAsesoriaById(@PathVariable Long id) {
        SesionAsesoriaDTO sesionAsesoriaDTO = sesionAsesoriaService.findById(id);
        return new ResponseEntity<>(sesionAsesoriaDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SesionAsesoriaDTO> createSesionAsesoria(@Valid @RequestBody SesionAsesoriaDTO sesionAsesoriaDTO) {
        SesionAsesoriaDTO createdSesionAsesoria = sesionAsesoriaService.save(sesionAsesoriaDTO);
        return new ResponseEntity<>(createdSesionAsesoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionAsesoriaDTO> updateSesionAsesoria(@PathVariable Long id, @Valid @RequestBody SesionAsesoriaDTO sesionAsesoriaDetails) throws Exception {
        SesionAsesoriaDTO updatedSesionAsesoria = sesionAsesoriaService.update(id, sesionAsesoriaDetails);
        return new ResponseEntity<>(updatedSesionAsesoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesionAsesoria(@PathVariable Long id) {
        sesionAsesoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/experto/{expertoId}")
    public ResponseEntity<List<SesionAsesoriaDTO>> getSesionesByExpertoId(@PathVariable Long expertoId) {
        List<SesionAsesoriaDTO> sesiones = sesionAsesoriaService.findSesionesByExpertoId(expertoId);
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }
}
