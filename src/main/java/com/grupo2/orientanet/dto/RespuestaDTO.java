package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDTO {

    private Long id;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 1, max = 500, message = "La descripción debe tener entre 1 y 500 caracteres")
    private String descripcion;

    @NotNull(message = "El ID de la pregunta no puede ser nulo")
    private Long preguntaId;  // Referencia al ID de Pregunta

    @NotNull(message = "El ID de la carrera no puede ser nulo")
    private Long carreraId;  // Referencia al ID de Carrera
}
