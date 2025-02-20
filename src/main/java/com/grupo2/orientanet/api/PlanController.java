package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.PlanDTO;
import com.grupo2.orientanet.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planes")
@PreAuthorize("hasRole('ESTUDIANTE')")
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAllPlanes() {
        List<PlanDTO> planes = planService.findAll();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getPlanById(@PathVariable Long id) {
        PlanDTO planDTO = planService.findById(id);
        return new ResponseEntity<>(planDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> createPlan(@Valid @RequestBody PlanDTO planDTO) {
        PlanDTO createdPlan = planService.save(planDTO);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanDTO planDetails) throws Exception {
        PlanDTO updatedPlan = planService.update(id, planDetails);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
