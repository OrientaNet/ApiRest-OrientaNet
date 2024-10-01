package com.grupo2.orientanet.dto;

import com.grupo2.orientanet.model.enums.Recurso;

public class RecursoEducativoRequestDTO {

    private String nombre;
    private String descripcion;
    private Recurso recurso;
    private String url;
    private Long expertoId; // Se envía el ID del experto asociado
}
