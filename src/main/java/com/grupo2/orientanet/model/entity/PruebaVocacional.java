package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pruebavocacional")
public class PruebaVocacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "pruebaVocacional", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_prueba_vocacional_estudiantes"))
    private Estudiante estudiante;


}
