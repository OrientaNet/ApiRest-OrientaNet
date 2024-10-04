package com.grupo2.orientanet.api;


import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.service.CarreraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    // Crear una nueva carrera
    @PostMapping
    public ResponseEntity<CarreraDTO> crearCarrera(@Valid @RequestBody CarreraDTO carrera) {
        CarreraDTO nuevaCarrera = carreraService.crearCarrera(carrera);
        return ResponseEntity.ok(nuevaCarrera);
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> obtenerCarrera(@PathVariable Long id) {
        CarreraDTO carrera = carreraService.obtenerCarreraPorId(id);
        return ResponseEntity.ok(carrera);
    }

    // Obtener todas las carreras
    @GetMapping
    public ResponseEntity<List<CarreraDTO>> obtenerTodasLasCarreras() {
        List<CarreraDTO> carreras = carreraService.obtenerTodasLasCarreras();
        return ResponseEntity.ok(carreras);
    }

    // Actualizar una carrera
    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> actualizarCarrera(
            @PathVariable Long id, @Valid
            @RequestBody CarreraDTO carreraDTO) throws Exception{
        CarreraDTO carreraActualizada = carreraService.actualizarCarrera(id, carreraDTO);
        return ResponseEntity.ok(carreraActualizada);
    }

    // Eliminar una carrera por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrera(@PathVariable Long id) {
        carreraService.eliminarCarrera(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
