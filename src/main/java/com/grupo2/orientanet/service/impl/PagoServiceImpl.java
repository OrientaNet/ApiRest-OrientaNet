package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.PagoMapper;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.EstadoPago;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.repository.PagoRepository;
import com.grupo2.orientanet.repository.SuscripcionRepository;
import com.grupo2.orientanet.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final SuscripcionRepository suscripcionRepository;
    private final PagoMapper pagoMapper;

    @Override
    public PagoDTO registrarPagoPendiente(Long suscripcionId, Double monto, MetodoPago metodoPago) {
        Suscripcion suscripcion = suscripcionRepository.findById(suscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));

        Pago nuevoPago = new Pago();
        nuevoPago.setSuscripcion(suscripcion);
        nuevoPago.setMonto(monto);
        nuevoPago.setFechaPago(null);  // Fecha será null hasta que se confirme el pago
        nuevoPago.setMetodoPago(metodoPago);
        nuevoPago.setEstadoPago(EstadoPago.PENDIENTE);  // Estado inicial: PENDIENTE

        Pago pagoGuardado = pagoRepository.save(nuevoPago);

        return pagoMapper.toDTO(pagoGuardado);
    }

    @Override
    public List<PagoDTO> obtenerHistorialDePagos(Long estudianteId) {
        List<Pago> pagos = pagoRepository.findByEstudianteId(estudianteId);
        return pagos.stream().map(pagoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PagoDTO confirmarPago(Long pagoId) {
        Pago pago = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));

        if (pago.getEstadoPago() != EstadoPago.PENDIENTE) {
            throw new RuntimeException("El pago ya ha sido confirmado o fallido.");
        }

        // Actualizar el pago a estado COMPLETADO
        pago.setEstadoPago(EstadoPago.COMPLETADO);
        pago.setFechaPago(LocalDate.now());  // Fecha actual de pago

        Pago pagoActualizado = pagoRepository.save(pago);
        return pagoMapper.toDTO(pagoActualizado);
    }
}
