package com.grupo2.orientanet.service;
import com.grupo2.orientanet.dto.SuscripcionDTO;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;
import java.util.List;

public interface SuscripcionService {
    SuscripcionDTO findById(Long id);
    Suscripcion suscribirEstudianteAPlan(Long estudianteId, Long planId, Double monto, MetodoPago metodoPago);
    Suscripcion renovarSuscripcion(Long suscripcionId, Double monto, MetodoPago metodoPago);
    SuscripcionDTO confirmarPagoYSuscripcion(Long pagoId);  // El método que mencionas
}
