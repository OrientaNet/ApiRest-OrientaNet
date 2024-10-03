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
public class PruebaVocacionalDTO {

    private Long id;

    @NotNull(message = "El nombre de la prueba no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;


    private List<Long> preguntaIds;
}
