package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.RecursoEducativoDTO;
import com.grupo2.orientanet.model.entity.RecursoEducativo;
import com.grupo2.orientanet.service.RecursoEducativoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<RecursoEducativoDTO>> getAllRecursos() {
        List<RecursoEducativoDTO> recursos = recursoEducativoService.getAll();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    // Obtener un recurso educativo por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecursoEducativoDTO> getRecursoById(@PathVariable Long id) {
        RecursoEducativoDTO recurso = recursoEducativoService.getById(id);
        return new ResponseEntity<>(recurso, HttpStatus.OK);

    }

    // Crear un nuevo recurso educativo
    @PostMapping
    public ResponseEntity<RecursoEducativoDTO> createRecurso(@Valid @RequestBody RecursoEducativoDTO recursoEducativoDTO) {
        RecursoEducativoDTO newRecurso = recursoEducativoService.save(recursoEducativoDTO);
        return new ResponseEntity<>(newRecurso, HttpStatus.CREATED);
    }


    // Eliminar un recurso educativo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable Long id) {
       recursoEducativoService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/recomendar/{carreraId}")
    public ResponseEntity<List<RecursoEducativoDTO>> recomendarRecursos(@PathVariable Long carreraId) {
        List<RecursoEducativoDTO> recursosRecomendados = recursoEducativoService.recomendarRecursosPorCarrera(carreraId);
        return ResponseEntity.ok(recursosRecomendados);

    }
}
