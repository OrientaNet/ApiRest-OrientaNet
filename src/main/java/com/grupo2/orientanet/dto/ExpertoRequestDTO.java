package com.grupo2.orientanet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertoRequestDTO {

    private String informacionPersonal;
    private String experiencia;
    private String certificaciones;
    private Long especializacionId;  // Solo el ID de Carrera (especialización)
    private Long usuarioId;  // Solo el ID de Usuario

}
