package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Estudiante  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //nuevos
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiantes_usuarios"))
    @JsonManagedReference
    private Usuario usuario;

    @Column(name = "informacion_personal", columnDefinition = "TEXT")
    private String informacionPersonal;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "intereses", columnDefinition = "TEXT")
    private String intereses;

    @OneToOne
    @JoinColumn(name = "resultado_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiante_resultado_test"))
    private ResultadoTest resultadoTest;

    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiante_carrera"))
    private Carrera carreraInteres;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;






//    @PrePersist
//    public void prePersist() {
//        createdAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
}
