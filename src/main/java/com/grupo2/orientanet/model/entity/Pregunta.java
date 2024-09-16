package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "pregunta_text", columnDefinition = "TEXT")
    private String enunciado;

    @ManyToOne
    @JoinColumn(name = "prueba_vocacional_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_pregunta_prueba_vocacional"))
    private PruebaVocacional pruebaVocacional;

}