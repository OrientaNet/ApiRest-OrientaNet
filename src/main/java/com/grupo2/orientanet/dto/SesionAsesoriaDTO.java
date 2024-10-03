package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SesionAsesoriaDTO {

    private Long id;

    @NotNull(message = "El tema no puede ser nulo")
    @Size(min = 1, max = 255, message = "El tema debe tener entre 1 y 255 caracteres")
    private String tema;

    @NotNull(message = "La URL no puede ser nula")
    @Size(min = 1, message = "La URL debe ser especificada")
    private String url;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDateTime fechaFin;

    @NotNull(message = "El ID de la carrera no puede ser nulo")
    private Long carreraId;

    @NotNull(message = "El ID del experto no puede ser nulo")
    private Long expertoId;
}
