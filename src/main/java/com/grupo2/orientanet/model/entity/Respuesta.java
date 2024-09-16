package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
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

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "valor")
    private int valor; // 1 para sistemas, 2 para medicina

    @ManyToOne
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    private Pregunta pregunta;
}
