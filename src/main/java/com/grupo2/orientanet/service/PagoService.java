package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.enums.MetodoPago;

import java.util.List;

public interface PagoService {
    PagoDTO registrarPagoPendiente(Long suscripcionId, Double monto, MetodoPago metodoPago);

    List<PagoDTO> obtenerHistorialDePagos(Long estudianteId);

    Pago confirmarPago(Long pagoId);
}

