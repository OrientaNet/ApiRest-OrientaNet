package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    // Relación con Carrera
    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "id")
    private Carrera carrera; // Asociación con Carrera

    // Relación con Pregunta
    @ManyToOne
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    private Pregunta pregunta;
}
