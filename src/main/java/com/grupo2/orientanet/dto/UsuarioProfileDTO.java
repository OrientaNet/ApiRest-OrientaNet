package com.grupo2.orientanet.dto;

import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UsuarioProfileDTO {

    private Long id;
    private String email;
    private ERole role; //El rol puede ser Estudiante o Experto
    private String nombre; //nombre del cliente
    private String apellido;

    private String informacionPersonal;
    //estudiante
    private String nivelAcademico;
    private String institucion;
    private String intereses;
    private Carrera carreraInteres;

    //experto
    private String experiencia;
    private String certificaciones;
    private Carrera carrera;

    /*
    mas weas
     */

}
