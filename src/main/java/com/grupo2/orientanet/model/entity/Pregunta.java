package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "preguntas")
public class    Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "pregunta_text", columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Respuesta> respuestas;

    @ManyToOne
    @JoinColumn(name = "prueba_id")
    @JsonBackReference
    private PruebaVocacional prueba;

    public void asignarPreguntaAOpciones() {
        if (respuestas != null) {
            for (Respuesta opcion : respuestas) {
                opcion.setPregunta(this);
            }
        }
    }
}