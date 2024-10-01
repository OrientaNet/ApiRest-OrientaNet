package com.grupo2.orientanet.dto;

public class ExpertoResponseDTO {

    private Long id;
    private String informacionPersonal;
    private String experiencia;
    private String certificaciones;

    // Datos de la carrera (especialización)
    private CarreraDTO especializacion;

    // Datos del usuario
    private UsuarioResponseDTO usuario;

    // Puedes incluir aquí una lista de recursos educativos si es necesario
}
