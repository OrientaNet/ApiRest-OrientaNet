package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;

public interface SuscripcionService {
    Suscripcion suscribirEstudianteAPlan(Long estudianteId, Long planId, Double monto, MetodoPago metodoPago);
    Suscripcion renovarSuscripcion(Long suscripcionId, Double monto, MetodoPago metodoPago);
}
