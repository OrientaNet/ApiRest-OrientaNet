package com.grupo2.orientanet.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expertos")
public class Experto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //nuevos
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;



    @Column(name = "informacion_personal", columnDefinition = "TEXT")
    private String informacionPersonal;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "certificaciones", columnDefinition = "TEXT")
    private String certificaciones;

    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_expertos_carreras"))
    private Carrera carrera;

    //
    //
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    //

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_estudiantes_usuarios"))
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "experto")
    private List<RecursoEducativo> recursoEducativo;

    @JsonIgnore
    @OneToMany(mappedBy = "experto")
    private List<SesionAsesoria> sesionAsesoria;
}
