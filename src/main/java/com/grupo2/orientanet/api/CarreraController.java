package com.grupo2.orientanet.api;


import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.service.CarreraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
@RequiredArgsConstructor
public class CarreraController {

    private final CarreraService carreraService;

    // Crear una nueva carrera
    @PostMapping
    public ResponseEntity<Carrera> crearCarrera(@RequestBody Carrera carrera) {
        Carrera nuevaCarrera = carreraService.crearCarrera(carrera);
        return ResponseEntity.ok(nuevaCarrera);
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> obtenerCarrera(@PathVariable Long id) {
        Carrera carrera = carreraService.obtenerCarreraPorId(id);
        return ResponseEntity.ok(carrera);
    }

    // Obtener todas las carreras
    @GetMapping
    public ResponseEntity<List<Carrera>> obtenerTodasLasCarreras() {
        List<Carrera> carreras = carreraService.obtenerTodasLasCarreras();
        return ResponseEntity.ok(carreras);
    }

    // Actualizar una carrera
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizarCarrera(
            @PathVariable Long id,
            @RequestBody Carrera carrera) throws Exception {
        Carrera carreraActualizada = carreraService.actualizarCarrera(id, carrera);
        return ResponseEntity.ok(carreraActualizada);
    }

    // Eliminar una carrera por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrera(@PathVariable Long id) {
        carreraService.eliminarCarrera(id);
        return ResponseEntity.noContent().build();
    }
}
