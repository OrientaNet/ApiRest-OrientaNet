package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.RecursoEducativo;
import com.grupo2.orientanet.service.RecursoEducativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recursos")
public class RecursoEducativoController {

    @Autowired
    private RecursoEducativoService recursoEducativoService;

    // Obtener todos los recursos educativos
    @GetMapping
    public ResponseEntity<List<RecursoEducativo>> getAllRecursos() {
        List<RecursoEducativo> recursos = recursoEducativoService.getAll();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    // Obtener un recurso educativo por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecursoEducativo> getRecursoById(@PathVariable Long id) {
        Optional<RecursoEducativo> recurso = recursoEducativoService.getById(id);
        return recurso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo recurso educativo
    @PostMapping
    public ResponseEntity<RecursoEducativo> createRecurso(@RequestBody RecursoEducativo recursoEducativo) {
        RecursoEducativo newRecurso = recursoEducativoService.saveOrUpdate(recursoEducativo);
        return new ResponseEntity<>(newRecurso, HttpStatus.CREATED);
    }

    // Actualizar un recurso educativo
    @PutMapping("/{id}")
    public ResponseEntity<RecursoEducativo> updateRecurso(@PathVariable Long id, @RequestBody RecursoEducativo recursoEducativo) {
        Optional<RecursoEducativo> existingRecurso = recursoEducativoService.getById(id);
        if (existingRecurso.isPresent()) {
            recursoEducativo.setId(id);  // Aseguramos que se actualice el recurso existente
            RecursoEducativo updatedRecurso = recursoEducativoService.saveOrUpdate(recursoEducativo);
            return new ResponseEntity<>(updatedRecurso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un recurso educativo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable Long id) {
        Optional<RecursoEducativo> recurso = recursoEducativoService.getById(id);
        if (recurso.isPresent()) {
            recursoEducativoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
