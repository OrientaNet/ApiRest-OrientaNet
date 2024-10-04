package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sesion_asesoria")
public class SesionAsesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tema", nullable = false)
    private String tema;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_sesion_asesoria_carreras"))
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "experto_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_sesion_asesoria_expertos"))
    private Experto experto;


}
