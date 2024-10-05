package com.grupo2.orientanet.service;

import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;

import java.util.List;

public interface PagoService {
    Pago registrarPago(Long suscripcionId, Double monto, MetodoPago metodoPago);
    List<Pago> obtenerHistorialDePagos(Long estudianteId);
}
