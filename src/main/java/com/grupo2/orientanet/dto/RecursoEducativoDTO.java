package com.grupo2.orientanet.dto;

import com.grupo2.orientanet.model.enums.Recurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecursoEducativoDTO {

    private Long id;
    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size(max = 50,message = "El nombre debe tener 50 caracteres o menos")
    private String nombre;
    private String descripcion;
    private Recurso recurso;
    @NotBlank(message = "El url debe ser obligatorio")
    private String url;
    private Long carreraId;
    private Long expertoId; // Se envía el ID del experto asociado
}
