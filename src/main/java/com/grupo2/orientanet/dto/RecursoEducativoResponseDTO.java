package com.grupo2.orientanet.dto;

public class RecursoEducativoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String recurso; // Representado como string (por ser un Enum)
    private String url;

    // Datos del experto asociado
    private ExpertoResponseDTO experto;
}
