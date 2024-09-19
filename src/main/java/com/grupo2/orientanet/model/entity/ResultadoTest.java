package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResultadoTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "recomendaciones", columnDefinition = "TEXT")
    private String recomendacion;

    @ManyToOne
    @JoinColumn(name = "prueba_vocacional_id", referencedColumnName = "id")
    private PruebaVocacional pruebaVocacional;

    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_resultado_test_carrera"))
    private Carrera carrera;
}
