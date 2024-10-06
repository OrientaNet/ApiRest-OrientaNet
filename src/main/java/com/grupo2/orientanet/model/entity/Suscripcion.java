package com.grupo2.orientanet.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grupo2.orientanet.model.enums.EstadoSuscripcion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Suscripcion")
public class Suscripcion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado_suscripcion")
    @Enumerated(EnumType.STRING)
    private EstadoSuscripcion estadoSuscripcion;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @JsonManagedReference  // Parte "dueña" de la relación
    @OneToMany(mappedBy = "suscripcion", cascade = CascadeType.ALL)
    private List<Pago> pagos;
}
