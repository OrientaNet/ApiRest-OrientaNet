package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.dto.SuscripcionDTO;
import com.grupo2.orientanet.mapper.SuscripcionMapper;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.service.PagoService;
import com.grupo2.orientanet.service.SuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;
    private final SuscripcionService suscripcionService;

    // Confirmar pago y activar suscripción
    @PostMapping("/confirmar-pago/{pagoId}")
    public ResponseEntity<SuscripcionDTO> confirmarPago(@PathVariable Long pagoId) {
        // Confirmamos el pago y activamos la suscripción
        SuscripcionDTO suscripcionDTO = suscripcionService.confirmarPagoYSuscripcion(pagoId);
        return ResponseEntity.ok(suscripcionDTO);
    }

    // Obtener historial de pagos de un estudiante
    @GetMapping("/historial-pagos/{estudianteId}")
    public ResponseEntity<List<PagoDTO>> obtenerHistorialPagos(@PathVariable Long estudianteId) {
        List<PagoDTO> pagosDTO = pagoService.obtenerHistorialDePagos(estudianteId);
        return ResponseEntity.ok(pagosDTO);
    }

    // Registrar un pago pendiente
    @PostMapping("/registrar-pago")
    public ResponseEntity<PagoDTO> registrarPago(@RequestParam Long suscripcionId, @RequestParam Double monto, @RequestParam MetodoPago metodoPago) {
        PagoDTO nuevoPagoDTO = pagoService.registrarPagoPendiente(suscripcionId, monto, metodoPago);
        return ResponseEntity.ok(nuevoPagoDTO);
    }
}


