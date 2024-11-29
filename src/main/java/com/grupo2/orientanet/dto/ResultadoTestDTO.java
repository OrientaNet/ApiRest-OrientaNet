package com.grupo2.orientanet.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoTestDTO {


    private Long id;

    private String recomendacion;

    private String pruebaNombre;

    private Long carreraId;

    private Long estudianteId;

    private Long usuarioId;
}
