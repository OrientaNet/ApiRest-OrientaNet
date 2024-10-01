package com.grupo2.orientanet.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expertos")
public class Experto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "informacion_personal", columnDefinition = "TEXT")
    private String informacionPersonal;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "certificaciones", columnDefinition = "TEXT")
    private String certificaciones;

    @ManyToOne
    @JoinColumn(name = "especializacion_id", referencedColumnName = "id")
    private Carrera especializacion;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_expertos_usuarios"))
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "experto")
    private List<RecursoEducativo> recursoEducativo;
}
