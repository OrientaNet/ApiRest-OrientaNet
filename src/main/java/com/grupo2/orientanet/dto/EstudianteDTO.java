package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDTO {

    private Long id;

    private String informacionPersonal;

    @NotBlank(message = "El nivel academico debe ser obligatorio")
    @Size(max = 100,message = "El nombre debe tener 100 caracteres o menos")
    private String nivelAcademico;

    @NotBlank(message = "La institucion debe ser obligatorio")
    @Size(max = 50,message = "La institucion debe tener 50 caracteres o menos")
    private String institucion;

    private String intereses;

    @NotBlank(message = "La carrera de interes debe ser obligatorio")
    @Size(max = 50,message = "La carrera de interes debe tener 50 caracteres o menos")
    private String carreraInteres;

    @NotBlank(message = "El id del usuario debe ser obligatorio")
    private Long usuarioId;
}
