package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.EstadoPago;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.repository.PagoRepository;
import com.grupo2.orientanet.repository.SuscripcionRepository;
import com.grupo2.orientanet.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Override
    public Pago registrarPago(Long suscripcionId, Double monto, MetodoPago metodoPago) {
        Suscripcion suscripcion = suscripcionRepository.findById(suscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));

        Pago nuevoPago = new Pago();
        nuevoPago.setSuscripcion(suscripcion);
        nuevoPago.setMonto(monto);
        nuevoPago.setFechaPago(LocalDate.now());
        nuevoPago.setMetodoPago(metodoPago);
        nuevoPago.setEstadoPago(EstadoPago.COMPLETADO);

        return pagoRepository.save(nuevoPago);
    }

    @Override
    public List<Pago> obtenerHistorialDePagos(Long estudianteId) {
        return pagoRepository.findByEstudianteId(estudianteId);
    }
}
