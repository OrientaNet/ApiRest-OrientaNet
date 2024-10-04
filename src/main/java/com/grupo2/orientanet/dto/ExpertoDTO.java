package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertoDTO {


    private Long id;

    private String informacionPersonal;

    @NotBlank(message = "La experiencia debe ser obligatoria")
    @Size(min = 10,message = "La experiencia debe tener 10 caracteres o mas")
    private String experiencia;

    private String certificaciones;
    
    private Long carreraId;  // Solo el ID de Carrera (especialización)

    private Long usuarioId;  // Solo el ID de Usuario

}
