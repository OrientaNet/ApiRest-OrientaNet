package com.grupo2.orientanet.dto;

import com.grupo2.orientanet.model.enums.EstadoSuscripcion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionDTO {

    private Long id;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDate fechaFin;

    @NotNull(message = "El estado de la suscripción no puede ser nulo")
    private EstadoSuscripcion estadoSuscripcion;

    @NotNull(message = "El ID del estudiante no puede ser nulo")
    private Long estudianteId;

    @NotNull(message = "El ID del plan no puede ser nulo")
    private Long planId;

    private Double totalPagos; // Example of aggregated data

}
