package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "pruebaVocacional", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<Pregunta> preguntas;


    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
        pregunta.setPruebaVocacional(this);
    }

    public void removePregunta(Pregunta pregunta) {
        this.preguntas.remove(pregunta);
        pregunta.setPruebaVocacional(null);
    }
}
