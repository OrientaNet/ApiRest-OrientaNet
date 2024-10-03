package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlanDTO {

    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double precio;

    @NotNull(message = "Los beneficios no pueden ser nulos")
    @Size(min = 1, message = "Los beneficios deben ser especificados")
    private String beneficios;

    @NotNull(message = "La duración en días no puede ser nula")
    @Min(value = 1, message = "La duración debe ser de al menos 1 día")
    private int duracionDias;

    private List<Long> suscripcionIds;
}
