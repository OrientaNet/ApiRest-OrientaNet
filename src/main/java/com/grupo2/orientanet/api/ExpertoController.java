package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.Experto;
import com.grupo2.orientanet.service.ExpertoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expertos")
public class ExpertoController {

    private final ExpertoService expertoService;

    @GetMapping
    public ResponseEntity<List<Experto>> getAllExpertos() {
        List<Experto> expertos = expertoService.findAll();
        return new ResponseEntity<>(expertos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experto> getExpertoById(@PathVariable Long id) {
        return expertoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Experto> createExperto(@Valid @RequestBody Experto experto) {
        Experto createdExperto = expertoService.save(experto);
        return new ResponseEntity<>(createdExperto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experto> updateExperto(@PathVariable Long id, @Valid @RequestBody Experto expertoDetails) {
        try {
            Experto updatedExperto = expertoService.update(id, expertoDetails);
            return ResponseEntity.ok(updatedExperto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperto(@PathVariable Long id) {
        if (expertoService.findById(id).isPresent()) {
            expertoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
