package com.grupo2.orientanet.model.entity;

import com.grupo2.orientanet.model.enums.Recurso;
import com.grupo2.orientanet.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recurso_educativo")
public class RecursoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Recurso recurso;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "experto_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_recurso_educativo_experto"))
    private Experto experto;
}
