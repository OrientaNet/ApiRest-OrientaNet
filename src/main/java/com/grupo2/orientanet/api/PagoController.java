package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/realizar")
    public ResponseEntity<Pago> realizarPago(
            @RequestParam Long suscripcionId,
            @RequestParam Double monto,
            @RequestParam MetodoPago metodoPago) {

        Pago nuevoPago = pagoService.registrarPago(suscripcionId, monto, metodoPago);
        return ResponseEntity.ok(nuevoPago);
    }

    @GetMapping("/historial/{estudianteId}")
    public ResponseEntity<List<Pago>> obtenerHistorialDePagos(@PathVariable Long estudianteId) {
        List<Pago> historialPagos = pagoService.obtenerHistorialDePagos(estudianteId);
        return ResponseEntity.ok(historialPagos);
    }
}

