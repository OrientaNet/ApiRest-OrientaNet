package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resultado_test")
public class ResultadoTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "recomendaciones", columnDefinition = "TEXT")
    private String recomendacion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prueba_vocacional_id", referencedColumnName = "id")
    private PruebaVocacional pruebaVocacional;

    @Column(name = "nombre_prueba")
    private String pruebaNombre;

    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_resultado_test_carrera"))
    private Carrera carrera;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_prueba_vocacional_estudiantes"))
    private Estudiante estudiante;

    @Column(name = "id_estudiante")
    private Long estudianteId;
}
