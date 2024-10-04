package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.SuscripcionDTO;
import com.grupo2.orientanet.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> findById(@PathVariable Long id) {
        SuscripcionDTO suscripcionDTO = suscripcionService.findById(id); // Utiliza el metodo del servicio
        return ResponseEntity.ok(suscripcionDTO);
    }
}
