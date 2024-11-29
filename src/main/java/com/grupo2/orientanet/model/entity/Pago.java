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

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estadoPago;

    // Associate the payment with a subscription
    @ManyToOne
    @JoinColumn(name = "suscripcion_id", nullable = false)
    private Suscripcion suscripcion;

    // Getters and Setters (optional if you're not relying solely on @Data)
}

