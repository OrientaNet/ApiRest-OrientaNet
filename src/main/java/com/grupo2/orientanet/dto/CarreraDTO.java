package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarreraDTO {

    private Long id;

    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size(min = 3,max = 100,message = "El nombre debe tener minimo 3 y maximo 100 caracteres")
    private String nombre;


    private String descripcion;

}
