package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.dto.SuscripcionDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.PagoMapper;
import com.grupo2.orientanet.mapper.SuscripcionMapper;
import com.grupo2.orientanet.model.entity.*;
import com.grupo2.orientanet.repository.*;
import com.grupo2.orientanet.service.SuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.EstadoSuscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.repository.SuscripcionRepository;
import com.grupo2.orientanet.service.PagoService;
import com.grupo2.orientanet.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
@RequiredArgsConstructor
public class SuscripcionServiceImpl implements SuscripcionService {


    private final SuscripcionRepository suscripcionRepository;
    private final SuscripcionMapper suscripcionMapper;
    private final PagoService pagoService;
    private final EstudianteRepository estudianteRepository;
    private final PlanRepository planRepository;
    private final PagoMapper pagoMapper;
    private final UsuarioRepository usuarioRepository;


    @Transactional(readOnly = true)
    @Override
    public SuscripcionDTO findById(Long id) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));
        return suscripcionMapper.toDTO(suscripcion);
    }

    @Transactional
    @Override
    public SuscripcionDTO suscribirEstudianteAPlan(Long usuarioId, Long planId, Double monto, MetodoPago metodoPago) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Estudiante estudiante = usuario.getEstudiante();

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado"));

        Suscripcion nuevaSuscripcion = new Suscripcion();
        nuevaSuscripcion.setEstudiante(estudiante);
        nuevaSuscripcion.setPlan(plan);
        nuevaSuscripcion.setFechaInicio(LocalDate.now());
        //TODO corregir fecha de fin e inicio.
        nuevaSuscripcion.setFechaFin(LocalDate.now().plusDays(plan.getDuracionDias()));
        nuevaSuscripcion.setEstadoSuscripcion(EstadoSuscripcion.PENDIENTE);

        // Save the Suscripcion
        Suscripcion savedSuscripcion = suscripcionRepository.save(nuevaSuscripcion);

        // Create and associate the Pago
        pagoService.registrarPagoPendiente(savedSuscripcion.getId(), monto, metodoPago);

        return suscripcionMapper.toDTO(savedSuscripcion);
    }


    @Transactional
    @Override
    public Suscripcion renovarSuscripcion(Long suscripcionId, Double monto, MetodoPago metodoPago) {
        Suscripcion suscripcion = suscripcionRepository.findById(suscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));

        suscripcion.setFechaFin(suscripcion.getFechaFin().plusDays(suscripcion.getPlan().getDuracionDias()));

        pagoService.registrarPagoPendiente(suscripcionId, monto, metodoPago);

        return suscripcionRepository.save(suscripcion);
    }

    @Transactional
    @Override
    public SuscripcionDTO confirmarPagoYSuscripcion(Long pagoId) {
        PagoDTO pagodto = pagoService.confirmarPago(pagoId);
        Pago pago = pagoMapper.toEntity(pagodto);

        Suscripcion suscripcion = pago.getSuscripcion();

        if (suscripcion.getEstadoSuscripcion() == EstadoSuscripcion.PENDIENTE) {
            suscripcion.setEstadoSuscripcion(EstadoSuscripcion.ACTIVA);
            suscripcion.setFechaInicio(LocalDate.now());
            suscripcion.setFechaFin(LocalDate.now().plusDays(suscripcion.getPlan().getDuracionDias()));
            suscripcion = suscripcionRepository.save(suscripcion);
        }

        return suscripcionMapper.toDTO(suscripcion);
    }

//    public List<Pago> getPagosBySuscripcion(Long suscripcionId) {
//        return pagoRepository.findPagosBySuscripcionId(suscripcionId);
//    }

}


