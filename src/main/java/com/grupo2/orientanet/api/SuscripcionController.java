package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.SuscripcionDTO;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ESTUDIANTE')")
@RequestMapping("/suscripciones")

public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

        @GetMapping("/{id}")
        public ResponseEntity<SuscripcionDTO> findById(@PathVariable Long id) {
            SuscripcionDTO suscripcionDTO = suscripcionService.findById(id); // Utiliza el metodo del servicio
            return ResponseEntity.ok(suscripcionDTO);
        }

        @PostMapping("/suscribir")
        public ResponseEntity<SuscripcionDTO> suscribirEstudianteAPlan(
                @RequestParam Long estudianteId,
                @RequestParam Long planId,
                @RequestParam Double monto,
                @RequestParam MetodoPago metodoPago) {

            SuscripcionDTO nuevaSuscripcion = suscripcionService.suscribirEstudianteAPlan(estudianteId, planId, monto, metodoPago);
            return ResponseEntity.ok(nuevaSuscripcion);
        }

        @PutMapping("/renovar")
        public ResponseEntity<Suscripcion> renovarSuscripcion(
                @RequestParam Long suscripcionId,
                @RequestParam Double monto,
                @RequestParam MetodoPago metodoPago) {

            Suscripcion suscripcionRenovada = suscripcionService.renovarSuscripcion(suscripcionId, monto, metodoPago);
            return ResponseEntity.ok(suscripcionRenovada);

        }
}
