package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaDTO {

    private Long id;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 1, max = 500, message = "La descripción debe tener entre 1 y 500 caracteres")
    private String descripcion;

    // IDs en lugar de entidades completas para las relaciones
    private List<Long> respuestaIds;

    @NotNull(message = "El ID de la prueba vocacional no puede ser nulo")
    private Long pruebaVocacionalId;
}
