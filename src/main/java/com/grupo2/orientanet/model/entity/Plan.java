package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "beneficios", nullable = false)
    private String beneficios;

    @Column(name = "duracion_dias", nullable = false)
    private int duracionDias;

    @JsonIgnore
    @OneToMany(mappedBy = "plan")
    private List<Suscripcion> suscripciones;  // Un plan puede estar asociado a muchas suscripciones.

    // Getters y Setters
}
