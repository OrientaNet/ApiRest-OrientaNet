package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.EstudianteDTO;
import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.service.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.findAll();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudianteDTO = estudianteService.findById(id);
        return new ResponseEntity<>(estudianteDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> createEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO createdEstudiante = estudianteService.save(estudianteDTO);
        return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(@PathVariable Long id, @Valid @RequestBody EstudianteDTO estudianteDetails) throws Exception {

            EstudianteDTO updatedEstudiante = estudianteService.update(id, estudianteDetails);
            return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        estudianteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
