package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "prueba", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Pregunta> preguntas;


    public void asignarPruebaAPreguntas() {
        if (preguntas != null) {
            for (Pregunta pregunta : preguntas) {
                pregunta.setPrueba(this);
                pregunta.asignarPreguntaAOpciones();
            }
        }
    }
}
