package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.ExpertoDTO;
import com.grupo2.orientanet.dto.RecursoEducativoDTO;
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
    public ResponseEntity<List<ExpertoDTO>> getAllExpertos() {
        List<ExpertoDTO> expertos = expertoService.findAll();
        return new ResponseEntity<>(expertos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpertoDTO> getExpertoById(@PathVariable Long id) {
        ExpertoDTO expertoDTO = expertoService.findById(id);
        return new ResponseEntity<>(expertoDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ExpertoDTO> createExperto(@Valid @RequestBody ExpertoDTO expertoDTO) {
        ExpertoDTO createdExperto = expertoService.save(expertoDTO);
        return new ResponseEntity<>(createdExperto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpertoDTO> updateExperto(@PathVariable Long id, @Valid @RequestBody ExpertoDTO expertoDetails) throws Exception{

        ExpertoDTO expertoDTO = expertoService.update(id, expertoDetails);

        return  new ResponseEntity<>(expertoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperto(@PathVariable Long id) {
        expertoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{expertoId}/recursos-educativos")
    public ResponseEntity<List<RecursoEducativoDTO>> obtenerRecursosEducativosPorExperto(@PathVariable Long expertoId) {
        List<RecursoEducativoDTO> recursos = expertoService.obtenerRecursosEducativosPorExperto(expertoId);
        return ResponseEntity.ok(recursos);
    }
}
