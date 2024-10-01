package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "carrera_interes", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiante_carrera"))
    private Carrera carreraInteres;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiantes_usuarios"))
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "resultado_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiante_resultado_test"))
    private ResultadoTest resultadoTest;

}
