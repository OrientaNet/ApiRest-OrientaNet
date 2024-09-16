package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Estudiante  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "informacion_personal", columnDefinition = "TEXT")
    private String informacionPersonal;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "intereses", columnDefinition = "TEXT")
    private String intereses;

    @Column(name = "carrera_Interes")
    private String carreraInteres;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiantes_usuarios"))
    private Usuario usuario;

}
