package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Plan;
import com.grupo2.orientanet.model.entity.Suscripcion;
import com.grupo2.orientanet.model.enums.EstadoSuscripcion;
import com.grupo2.orientanet.model.enums.MetodoPago;
import com.grupo2.orientanet.repository.EstudianteRepository;
import com.grupo2.orientanet.repository.PlanRepository;
import com.grupo2.orientanet.repository.SuscripcionRepository;
import com.grupo2.orientanet.service.PagoService;
import com.grupo2.orientanet.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class SuscripcionServiceImpl implements SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Suscripcion suscribirEstudianteAPlan(Long estudianteId, Long planId, Double monto, MetodoPago metodoPago) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        Suscripcion nuevaSuscripcion = new Suscripcion();
        nuevaSuscripcion.setEstudiante(estudiante);
        nuevaSuscripcion.setPlan(plan);
        nuevaSuscripcion.setFechaInicio(LocalDate.now());
        nuevaSuscripcion.setFechaFin(LocalDate.now().plusDays(plan.getDuracionDias()));
        nuevaSuscripcion.setEstadoSuscripcion(EstadoSuscripcion.ACTIVA);

        nuevaSuscripcion = suscripcionRepository.save(nuevaSuscripcion);

        pagoService.registrarPago(nuevaSuscripcion.getId(), monto, metodoPago);

        return nuevaSuscripcion;
    }

    @Override
    public Suscripcion renovarSuscripcion(Long suscripcionId, Double monto, MetodoPago metodoPago) {
        Suscripcion suscripcion = suscripcionRepository.findById(suscripcionId)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));

        suscripcion.setFechaFin(suscripcion.getFechaFin().plusDays(suscripcion.getPlan().getDuracionDias()));
        pagoService.registrarPago(suscripcionId, monto, metodoPago);

        return suscripcionRepository.save(suscripcion);
    }
}
