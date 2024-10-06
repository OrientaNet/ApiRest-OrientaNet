package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grupo2.orientanet.model.enums.EstadoPago;

import com.grupo2.orientanet.model.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "metodo_pago")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING) // Para manejar diferentes estados de pago
    @Column(name = "estado_pago")
    private EstadoPago estadoPago;


    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JsonBackReference  // Parte inversa de la relación
    @JoinColumn(name = "suscripcion_id")
    private Suscripcion suscripcion;

    // Getters y Setters
}
